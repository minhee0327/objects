# Chapter14 > 일관성 있는 협력

## 📌 학습 내용 요약   
객체지향 패러다임의 장점은 설계를 재사용할 수 있다는 점.  
설계의 재사용은 트레이드 오프가 있다. (공짜로 얻어지지 않는다.)  
### 1. 핸드폰 과금 시스템 변경하기 (늘어난 기본 정책) 
비일관성은 두가지 상황에서 발목을 잡는다.
1. 새로운 구현을 추가해야할 때 요구사항 관점에서 여러개의 규칙을 사용한다는 공통점을 공유하지만, 구현방식은 완전 다르다.
2. 기존의 구현을 이해해기 어렵다.

유사한 기능은 서로 다른 방식으로 구현해서는 안된다.

### 2. 설계에 일관성 부여하기
일관성 있는 설계를 만드는데 가장 훌륭한 조언
1. 다양한 설계 경험을 쌓아라
2. 디자인 패턴을 학습하고 변경이라는 문맥안에서 적용해보라.

협력을 일관성있게 만들기 위한 기본지침
1. 변하는 개념을 변하지 않는 개념으로부터 분리하라.
2. 변하는 개념을 캡슐화하라.

- 절차지향: 조건문의 분기추가 / 개별 분기로직 수정
- 객체지향: 조건로직을 객체사이의 이동으로 변경

조건로직을 객체사이의 이동으로 변경하려면, 커다란 클래스를 더 작은 클래스로 분리해야한다. 클래스를 분리하기 위해 어떤 기준을 따르는게 좋을까? ⇒ 변경의 이유와 주기 ⇒ 클래스는 명확히 단 하나의 이유에 의해서만 변경되어야하고, 클래스 안의 모든 코드는 함께 변경 되어야한다. (SRP) 

캡슐화는 단순히 데이터를 감추는게 아니라, 소프트웨어 안에서 변할 수 있는 모든 ‘개념’을 감추는 것
- 데이터 캡슐화: 클래스의 인스턴스 변수에 private (외부접근 막음)
- 메서드 캡슐화: 클래스에 정의된 메서드에 protected (외부 접근을 막고 클래스, 서브클래스만 접근)
- 객체 캡슐화: 예: Movie class안에 DiscountPolicy라는 타입을 인스턴스 변수로 포함 (합성)
- 서브타입 캡슐화: 예: Movie는 DiscountPolicy만 알고있을 뿐, DiscountPolicy를 상속받은 구체클래스를 알 수 없다. (다형성)

## 💡 느낀 점
- 캡슐화를 잘 해야겠다. 유지보수를 하거나 신규기능이 추가 될 때 모든 코드를 이해하지 않고, 흐름을 파악하고 필요한 부분만 수정이 가능해진다는 강점을 배웠다.  
  (변하는 부분만 따로 떼어내어 독립적으로 이해하더라도 전체 구조를 쉽게 이해할 수 있다.)
- 설계를 약간 비트는 것이 조금 이상한 구조를 낳더라도 전체적으로 일관성을 유지할 수 있는 설계를 선택하는것이 현명하다.
- 디자인패턴을 다시 공부하게 된다면, 변경의 축 (변하는 개념 / 변하지 않는 개념)을 기준으로 캡슐화를 하는 과정에 의한 것인지 살펴보면 좋을 것 같다.  

## ❓ 궁금한 점
- (전체 흐름상 중요하진 않은것 같긴하지만.. 😅) p482 문장들 중에 days() 의 반환값이 1보다 크다면이니까 아래 코드에서 if(days() > 1)이 되어야 하지 않는지..? 

## 🔗 참고 링크
