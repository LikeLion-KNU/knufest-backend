package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.booth.repository.BoothRepository;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import LlikelionKNU.KNUfest.domain.user.entity.UserEntity;
import LlikelionKNU.KNUfest.domain.user.repository.UserBoothRepository;
import LlikelionKNU.KNUfest.domain.user.service.UserService;
import LlikelionKNU.KNUfest.global.error.NoExistException;
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

    private final CommentService commentService;
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
                                .id(booth.getId())
                                .boothName(booth.getBoothName())
                                .likes(booth.getLikes())
                                .Likable(true)
                        .build());
            }

            if(!userBoothEntityList.isEmpty()){
                for(UserBoothEntity userBooth : userBoothEntityList){
                    Booth tempbooth = boothDtos.get(userBooth.getBooth().getId().intValue()-1);
                    tempbooth.setLikable(false);
                    boothDtos.set(userBooth.getBooth().getId().intValue()-1, tempbooth);
                }
            }


            return AllBooth.builder()
                    .count(boothDtos.size())
                    .boothDtoes(boothDtos)
                    .build();
        }
    }

    @Override
    public BoothDetail getBooth(Long boothId, String userHash) {
        Optional<BoothEntity> boothOp;

        Optional<UserBoothEntity> userBoothEntity = userBoothRepository.findByUserIdAndBoothId(userService.getUserByHash(userHash).getId(), boothId);
        BoothEntity booth;

        boolean temp;
        if(userBoothEntity.isEmpty()){
            boothOp = boothrepository.findById(boothId);
            temp = true;
            if(boothOp.isEmpty()){
                throw new NoExistException("해당 부스 정보가 없습니다. (id 확인요망)");
            }else{
                booth = boothOp.get();
            }
        }else{
            temp = false;
            booth = userBoothEntity.get().getBooth();
        }
            return BoothDetail.builder()
                    .id(booth.getId())
                    .boothName(booth.getBoothName())
                    .likes(booth.getLikes())
                    .urls(booth.getUrls())
                    .Likable(temp)
                    .comments(commentService.getCommentPage(booth.getId(),5,1, "default", userHash))
                    .build();
    }

    @Override
    public String updateLikes(Long boothId, String userHash) {

        UserEntity user = userService.getUserByHash(userHash);
        Optional<UserBoothEntity> userBoothEntity = userBoothRepository.findByUserIdAndBoothId(user.getId(), boothId);
        UserBoothEntity userBooth;

        BoothEntity booth;

        if(userBoothEntity.isEmpty()){
            Optional<BoothEntity> boothOp = boothrepository.findById(boothId);

            if(boothOp.isEmpty()){
                throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
            }else{
                booth = boothOp.get();
                booth.setLikes(booth.getLikes()+1);
                boothrepository.save(booth);

                userBooth = UserBoothEntity.builder()
                        .booth(booth)
                        .user(user)
                        .build();

                userBoothRepository.save(userBooth);
                return "좋아요를 업데이트(+1) 하였습니다.";
            }


        }else{
            booth = userBoothEntity.get().getBooth();
            booth.setLikes(booth.getLikes()-1);
            boothrepository.save(booth);

            userBoothRepository.delete(userBoothEntity.get());

            return "좋아요를 업데이트(-1) 하였습니다.";
        }

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
}
