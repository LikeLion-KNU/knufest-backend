package LlikelionKNU.KNUfest.domain.booth.service;

import LlikelionKNU.KNUfest.domain.booth.dto.AllBooth;
import LlikelionKNU.KNUfest.domain.booth.dto.BoothDetail;
import LlikelionKNU.KNUfest.domain.booth.dto.Booth;
import LlikelionKNU.KNUfest.domain.booth.entity.BoothEntity;
import LlikelionKNU.KNUfest.domain.booth.repository.BoothRepository;
import LlikelionKNU.KNUfest.domain.comment.service.CommentService;
import LlikelionKNU.KNUfest.domain.user.entity.UserBoothEntity;
import LlikelionKNU.KNUfest.domain.user.service.UserBoothService;
import LlikelionKNU.KNUfest.domain.user.service.UserService;
import LlikelionKNU.KNUfest.domain.user.service.UserServiceImpl;
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
    private final CommentService commentService;
    private final UserBoothService userBoothService;
    private final UserService userService;

    @Override
    public AllBooth getAllbooth(String userHash) {

        List<BoothEntity> boothes = boothrepository.findAll();
        List<Booth> boothDtos;

        List<UserBoothEntity> userBoothEntityList = userBoothService.getAllUserBooth(
                userService.getUserByHash(userHash).getId());

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

            for(UserBoothEntity userBooth : userBoothEntityList){
                Booth tempbooth = boothDtos.get(userBooth.getBoothEntity().getId().intValue());
                tempbooth.setLikable(false);
                boothDtos.set(userBooth.getBoothEntity().getId().intValue(), tempbooth);
            }

            return AllBooth.builder()
                    .count(boothDtos.size())
                    .boothDtoes(boothDtos)
                    .build();
        }
    }

    @Override
    public BoothDetail getBooth(Long id, String userHash) {
        Optional<BoothEntity> boothOp = boothrepository.findById(id);

        Optional<UserBoothEntity> userBoothEntity = userBoothService.getUserBooth(id, userService.getUserByHash(userHash).getId());

        boolean temp = userBoothEntity.isEmpty();

        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인요망)");
        }else{
            BoothEntity booth = boothOp.get();

            return BoothDetail.builder()
                    .id(booth.getId())
                    .boothName(booth.getBoothName())
                    .likes(booth.getLikes())
                    .urls(booth.getUrls())
                    .Likable(temp)
                    .comments(commentService.getCommentPage(boothOp.get().getId(),5,1, "default"))
                    .build();
        }
    }

    @Override
    public void updateLikes(Long id) {

        Optional<BoothEntity> boothOp = boothrepository.findById(id);
        BoothEntity booth;

        if(boothOp.isEmpty()){
            throw new NoExistException("해당 부스 정보가 없습니다. (id 확인 요망)");
        }else{
            booth = boothOp.get();
            booth.setLikes(booth.getLikes()+1);
            boothrepository.save(booth);
        }
    }
}
