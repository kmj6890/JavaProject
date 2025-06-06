package model;

public interface Purchasable {
    int getPrice();                   // 아이템 가격
    void apply(Character c);// 아이템 사용 시 캐릭터에 적용
    void rollback(Character c);
    String getName();                // 아이템 이름
}
