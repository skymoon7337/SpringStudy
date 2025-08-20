package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;


public class AutowiredTest {

    @Test
    void autowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }


    static class TestBean{

        @Autowired(required = false)
        public void setNobean1(Member nobean1){
            System.out.println("nobean1 = " + nobean1);
        }

        @Autowired
        public void setNobean2(@Nullable Member nobean2){
            System.out.println("nobean2 = " + nobean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }


    }
    
}
