package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 아닐 경우 할인 X")
    void vip_x(){
        //given
        Member member2 = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount2 = rateDiscountPolicy.discount(member2, 10000);
        //then
        assertThat(discount2).isEqualTo(0);
    }
}