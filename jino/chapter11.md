# Week 6 - chapter 11 합성과 유연한 설계

## 📌 학습 내용 요약
- 상속과 합성은 객체지향 프로그래밍에서 가정 널리 사용되는 코드 재사용 기법
- 상속 : 부모 클래스와 자식 클래스를 연결, 부모 클래스 코드 재사용 ** is-a
- 합성 : 전체를 표현하는 객체가 부분을 표하는 객체를 포함해서 부분 객체의 코드를 재사용 ** has-a
- 포워딩 메서드: 기존 클래스의 인터페이스를 그대로 외부에 제공하면서 구현에 대한 결합 없이 일부 작동 방식을 변경하고 싶은 경우에 사용할 수 있는 유용한 기법
- 상속의 남용으로 하나의 기능을 추가하기 위해 필요 이상으로 많은 수의 클래스를 ㅈ추가해야 하는 경우를 가리켜 클래스 폭발 문제 또는 조합의 폭발 문제라고 부른다.
- 합성은 컴파일타임 관계를 런타임 관계로 변경함으로써 문제를 해결
- 합성을 사용하면 구현이 아닌 퍼블릭 인터페이스에 대해서만 의존할 수 있기 때문에 런타임에 객체의 관계를 변경할 수 있다.
- 그렇다면 상속은 사용해서는 안되는 것인가, 상속을 이용하는 경우는 언제인가
  - 구현 상속에 국한된다는 점을 이해하고, 인터페이스 상속을 이해해야 한다.
- 믹스인: 이해 안됨

## 💡 느낀 점
- 믹스인은 조금 이해가 되진 않았다.
- 상속이아닌 합성을 이용 할 때, 포인트가 뭐일지 고민해보자
  - 요구사항을 만족시키기 위해 상속이라는 기술로 주어진 문제를 해결한다 라는 느낌을 받았다.
  - 합성은, 생성자에 반복적으로 계산해야 되는 항목을 주입 받는다는 것을 알 수 있다.
    - 합성하는 객체의 타입을 인터페이스로나 추상클래스로 선언하고 의존성 주입을 사용해 런타임에 필요한 객체를 설정 할 수 있도록 구현하는것이 키포인트
    - 인스턴스 변수로 마찬가지로 인터페이스나 추상클래스를 주입 받아 다음 계산에 활용하는 것도 키포인트 (super 가 아닌)
    - 그렇다고 상속을 안쓴건 아니지만....


## ❓ 궁금한 점
- 근데 phone 에 calls 는 누가 넣어주냐.....
- 이거 상속과 합성이야기 맞는건가... 그냥 어떻게 설계했냐의 차이 아닌가... 둘다 상속을 사용하긴 한건데...
- new a(new b(new c))) : c 실행하고 b 실행하고 a 실행...
- p376
  - Phone phone = new Phone(new TaxablePolicy(0.05, new RegularPolicy(...));
  - phone.calculateFee() 실행 시
    1. TaxablePilcy 의 부모클래스인 AdditionalRatePolicy 의 calculateFee 실행 (p.374)
    2. next.calculateFee 실행 : next = RegularPolcy, 즉 RegularPolicy.calculateFee 실행 (p.370)
    3. RegularPolicy.calculateFee 는 BasicRatePolicy 상속이므로, BasicRatePolicy 의 calculateFee 실행 (p.369)
    4. BasicRatePolicy.calculateFee 에서는 추상메서드인 calculateCallFee 를 호출하므로, 다시 RegularPolicy.calculateCallFee 호출 (p.370)
    5. 그다음 TaxablePolicy 의 부모인 AdditionalRatePolicy 의 calculateFee 에서 다음 라인인 afterCalculated(fee) 실행 (p.374) 
    6. 추상 메서드이기 때문에 다시 taxablePolicy 의 afterCalculated 실행
 즉 -> RegularPolicy(BasicRatePolicy).calculateFee 실행 > TaxablePolicy(AdditionalRatePolicy).afterCalculated 실행.....

이거 맞나? 더 복잡해지는 것은 아닌지...

FG_auth 를 상속이 아닌 합성으로 재구성해보기

  

## 🔗 참고 링크
- 
