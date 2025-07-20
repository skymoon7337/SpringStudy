package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();



    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll(){
        Member member3 = new Member();
        member3.setName("Sky3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("Sky4");
        repository.save(member4);

        List<Member> result = repository.findAll();
        //Assertions.assertThat(result).isEqualTo(repository.findAll());

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}
