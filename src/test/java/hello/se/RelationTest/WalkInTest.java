package hello.se.RelationTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
@Rollback(false)
public class WalkInTest {
    @Autowired
    WalkInRepository walkInRepository;

    @Test
    public void Walk_저장() throws Exception {
        //given
        WalkIn saveWalk = walkInRepository.save();
        //when
        Assertions.assertThat(saveWalk.getOid()).isEqualTo(1);
        //then
    }

    @Test
    public void Walk_조회() throws Exception {
        //given
        WalkIn walk1 = new WalkIn();
        walk1.setOid(1);
        walk1.setCovers(3);

        WalkIn walk2 = new WalkIn();
        walk2.setOid(2);
        walk2.setCovers(4);

        WalkIn walk3 = new WalkIn();
        walk3.setOid(3);
        walk3.setCovers(3);


        walkInRepository.save(walk1);
        walkInRepository.save(walk2);
        walkInRepository.save(walk3);
        //when

        List<WalkIn> all = walkInRepository.findAll();

        //then
        Assertions.assertThat(3).isEqualTo(all.size());

        covers_조회_not_rollback();
    }

    private void covers_조회_not_rollback() throws Exception {
        //given
        List<WalkIn> forCovers = walkInRepository.findForCovers(3);

        Optional<WalkIn> walk = forCovers.stream()
                .filter(w -> w.getCovers().equals(3)).findFirst();

        //when
        Assertions.assertThat(walk.get().getOid()).isEqualTo(1);
        //then

    }
}
