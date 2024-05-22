package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothLike;
import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.booth.repository.BoothRepository;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserBoothRepository;
import LlikelionKNU.KNUfest.domain.user.service.UserService;
import LlikelionKNU.KNUfest.global.error.NoExistException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoothServiceImpl implements BoothService{

    private final BoothRepository boothrepository;
    private final UserBoothRepository userBoothRepository;

    private final UserService userService;

    @Override
    public AllBooth getAllbooth(String userHash) {

        List<BoothEntity> boothes = boothrepository.findAll();
        List<Booth> boothDtos;

        List<UserBoothEntity> userBoothEntityList = userBoothRepository.findAllByUserId(userService.getUserByHash(userHash).getId());

        if(boothes.isEmpty()) {
            throw new NoExistException("부스 전체 정보가 없습니다.");
        }else{
            boothDtos = new ArrayList<>();
            for(BoothEntity booth : boothes){
                boothDtos.add(Booth.builder()
                                .boothName(booth.getBoothName())
                                .host(booth.getHost())
                                .likes(booth.getLikes())
                                .categori(booth.getCategori())
                                .boothnum(booth.getBoothnum())
                                .Likable(true)
                        .build());
            }

            // Likable Service
            if(!userBoothEntityList.isEmpty()){
                for(UserBoothEntity userBooth : userBoothEntityList){

                    BoothEntity booth = userBooth.getBooth();
                    int index = boothes.indexOf(booth);
                    Booth tempbooth = boothDtos.get(index);
                    tempbooth.setLikable(false);
                    boothDtos.set(index, tempbooth);
                }
            }


            return AllBooth.builder()
                    .count(boothDtos.size())
                    .boothDtoes(boothDtos)
                    .build();
        }
    }

    @Override
    public BoothDetail getBooth(int boothnum, String categori, String userHash) {
        Optional<BoothEntity> boothOp = boothrepository.findByBoothnumAndCategori(boothnum, categori);

        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }

        Optional<UserBoothEntity> userBoothEntity = userBoothRepository.findByUserIdAndBoothId(userService.getUserByHash(userHash).getId(), boothOp.get().getId());
        BoothEntity booth;

        boolean temp;

        if(userBoothEntity.isEmpty()){
            temp = true;
            booth = boothOp.get();

        }else{
            temp = false;
            booth = userBoothEntity.get().getBooth();
        }
            return BoothDetail.builder()
                    .boothName(booth.getBoothName())
                    .host(booth.getHost())
                    .boothDescription(booth.getDescription())
                    .likes(booth.getLikes())
                    .commentCount(booth.getCommentCount())
                    .boothnum(booth.getBoothnum())
                    .categori(booth.getCategori())
                    .urls(booth.getUrls())
                    .Likable(temp)
                    .build();
    }

    @Override
    public BoothLike updateLikes(int boothnum, String categori, String userHash) {

        Optional<BoothEntity> boothOp = boothrepository.findByBoothnumAndCategori(boothnum, categori);
        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }

        UserEntity user = userService.getUserByHash(userHash);
        Optional<UserBoothEntity> userBoothEntity = userBoothRepository.findByUserIdAndBoothId(user.getId(), boothOp.get().getId());
        UserBoothEntity userBooth;

        BoothEntity booth;

        BoothLike boothLikeDto;
        String message;

        if(userBoothEntity.isEmpty()){

            booth = boothOp.get();
            booth.setLikes(booth.getLikes()+1);
            boothrepository.save(booth);

            userBooth = UserBoothEntity.builder()
                    .booth(booth)
                    .user(user)
                    .build();

            userBoothRepository.save(userBooth);


            message = "좋아요를 업데이트(+1) 하였습니다.";

        }else{
            booth = userBoothEntity.get().getBooth();
            booth.setLikes(booth.getLikes()-1);
            boothrepository.save(booth);

            userBoothRepository.delete(userBoothEntity.get());

            message = "좋아요를 업데이트(-1) 하였습니다.";
        }

        boothLikeDto = BoothLike.builder()
                .likeNum(booth.getLikes())
                .message(message)
                .build();

        return boothLikeDto;
    }

    @Override
    public BoothEntity findById(Long id){
        Optional<BoothEntity> boothOp = boothrepository.findById(id);
        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            return boothOp.get();
        }
    }

    @Override
    public BoothEntity findByBoothnumAndCategori(int boothnum, String categori){
        Optional<BoothEntity> boothOp = boothrepository.findByBoothnumAndCategori(boothnum, categori);
        if(boothOp.isEmpty()){
            throw new NoExistException(("해당 부스 정보가 없습니다. (id 확인 요망)"));
        }else{
            return boothOp.get();
        }
    }
}
