package hello.core.singleton;

public class StatefulService {

//    private int price; // 전체에서 공유되는 필드(사실상 전역 변수처럼 동작)

    public int order(String name, int price){
        System.out.println("name = "+name+" price = "+price);
//        this.price = price; // 값을 저장하는 여기가 문제
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}
