# 부록 A > 계약에 의한 설계

## 📌 학습 내용 요약   
### 계약에 의한 설계 (Design by Contract, DBC)
- 계약은 협력에 참여하는 두 객체사이의 의무와 이익을 문서화하는것.
- 인터페이스에 대해 프로그래밍하라는 원칙을 확장한 것.

- **사전조건**: 메서드가 호출되기 위해 만족돼야하는 조건, 클라이언트의 의무, 메서드에 들어온 인자의 정합성을 체크하기 위해 사용.
- **사후조건**: 메서드가 실행된 후에 클라이언트에게 보장해야하는 조건. 
  - 사용 의도
    - 인스턴스 변수의 상태가 올바른지 
    - 전달된 파라미터의 값이 올바르게 변경됐는지
    - 반환값이 올바른지?
  - 사전조건보다 어려운이유
    - 한 메서드안에 여러 return문이 나오는 경우
    - 실행 전/후의 값을 비교해야하는 경우
- **불변식**: 인스턴스 생명주기 전반에 걸쳐 지켜져야하는 규칙, 일반적으로 객체의 내부 상태와 관련이 있다.
  - 모든 인스턴스가 생성된 후에 만족돼야한다.
  - 메서드 실행 전과 종류후에는 항상 불변식을 마족하는 상태가 유지되어야한다.

### 계약에 의한 설계와 서브타이핑
리스코프 치환원칙의 규칙을 2가지 종류로 세분화: **계약 규칙 / 가변성 규칙**

#### 계약 규칙
> 슈퍼타입과 서브타입 사이의   
> 사전조건, 사후조건, 불변식에 대해 서술할 수 있는 제약에 관한 규칙
- 서브타입에 더 강력한 사전조건을 정의할 수 없다.
- 서브타입에 더 완화된 사후조건을 정의할 수 없다.
- 슈퍼타입의 불변식은 서브타입에서도 반드시 유지되어야한다.
- Fail Fast: 차라리 문제가 발생한 위치에서 프로그램이 실패하도록 만들어라. 문제의 원인을 파악하는 가장 빠른 방법은 문제가 발생하자마자 프로그램이 일찍 실패하게 만드는 것이다.

#### 가변성 규칙
> 파라미터와 리턴타입의 변형과 관련된 규칙
- 서브타입은 슈퍼타입이 발생시키는 예외와 다른 타입의 예외를 발생시켜서는 안된다. 
- 서브타입의 리턴타입은 공변성을 가져야한다. 
- 서브타입의 메서드 파라미터는 반공변성을 가져야한다. (자바는 파라미터 반공변성을 허용하진 않음.)

#### 함수타입과 서브타이핑
- 이름없는 메서드 정의: 익명 함수, 함수 리터럴, 람다 표현식
- 메서드 타입/함수타입: 
  ```skala
  IndependentPublisher => Book
  ```
- 메서드 타입에서 정의한 시그니처를 준수하는 메서드들은 인스턴스로 간주한다.
  ```skala
  def sell(publisher: IndependentPublisher): Book = new Book(publisher)
  ```
  ```skala
  (publisher: IndependentPublisher) => new Book(publisher)
  ```
- 함수타입(메서드타입)의 서브타입을 정의할 수 있는가? yes..!
  ```skala
  new Orderer().order((publisher: IndependentPublisher) => new Book(publisher))
  new Orderer().order((publisher: Publisher) => new Magazine(publisher))
  ```
  - 파라미터 타입 반공병적
  - 리턴타입 공변적

## 💡 느낀 점
- 가변성 규칙을 이해하는데 어려웠던 것 같습니다.. 😹

## ❓ 궁금한 점
* p.566 마지막에서 3번째 줄: 자바는 리턴타입 공변성을 지원하는데, C#은 리턴타입 공변성을 지원하지 않는다..? (C#도 자바와 마찬가지로 제네릭을 활용하면 가능하다)
* p.570 마지막에 new Orderer() -> Customer() 인거겠죠...??: yes!
* p.571 에서 자바의 경우 new Customer().order((publisher: Publisher => new Magazine(publisher))가 될 수 있는지?
  * 결론(답변) : 자바는 파라미터 반공변성을 지원하지 않는다. 대신, <? super Publiher> 처럼 제네릭을 이용해서 사용할 수 있다.

## 🔗 참고 링크
사후조건 p543 마지막 코드 (Contract.Ensure 사용한)를 자바로 바꿔달라고 GPT에 문의
```java
public class Audience {
    private Bag bag;

    public BigDecimal buy(Ticket ticket) {
        BigDecimal result;

        if (bag.isInvited()) {
            bag.setTicket(ticket);
            result = BigDecimal.ZERO;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            result = ticket.getFee();
        }

        return Contract.ensure(result, r -> r.compareTo(BigDecimal.ZERO) >= 0, "Buy result must be >= 0");
    }
}

public class Contract {
  public static <T> T ensure(T result, Predicate<T> condition, String message) {
    if (!condition.test(result)) {
      throw new IllegalStateException(message);
    }
    return result;
  }
}
```
불변식은 자바로 어떻게 이해하냐고 했을 때 답변
```java
public class Invitation {
    private final LocalDateTime when;

    public Invitation(LocalDateTime when) {
        this.when = when;
        checkInvariant(); // 생성자에서 무결성 검사
    }

    public LocalDateTime getWhen() {
        checkInvariant();  // 메서드 수행 전후에 불변성 유지 확인
        return when;
    }

    private void checkInvariant() {
        if (this.when == null) {
            throw new IllegalStateException("when must never be null");
        }
    }
}
```
