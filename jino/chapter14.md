# Week 8 - chapter 14 일관성 있는 협력

## 📌 학습 내용 요약
- 객체는 협력을 위해 존재한다.
- 협력은 객체가 존재하는 이유와 문맥을 제공한다.
- 이해하기 쉽고, 수정이 쉽고, 재사용 가능해야한다.
- 일관성 있는 협력 패턴을 적용하면 여러분의 코드가 이해하기 쉽고 직관적이며 유연해진다는 것이 이번 장의 주제다.
- 일관성 없는 코드의 문제점
  - 새로운 기본 정책을 추가하면 추가할수록 코드 사이의 일관성이 점점 더 어긋난다
  - 일관성 없는 코드는 이해하기 어렵다
- 유사한 기능을 서로 다른 방식으로 구현해서는 안된다.
- 변하는 개념을 변하지 않는 개념으로 분리하라
- 변하는 개념을 캡슐화하라.
- 캡슐화란 변하는 어떤 것이든 감추는 것?
  - 퍼블릭 인터페이스와 구현을 분리
  - 변하지 않는 부분의 일부로 타입 계층을 합성한다
- 협력을 일관성 있게 만들면, 공통 코드의 구조와 협력 패턴이 모든 기본 정책에 걸쳐 동일하기 때문에 코드를 한번 이해하면, 이 지식을 다른 코드를 이해하는 데 그대로 적용할 수 있다.
- 유사한 기능에 대해 유사한 협력 패턴을 적용하는 것은 객체지향 시스템에서 개념적 무결성을 유지할 수 있는 가장 효과적인 방법이다.
- 지속적인 개선
  - 처음에는 일관성을 유지하는 것처럼 보이던 협력 패턴이 시간이 흐르면서 새로운 요구사항이 추가 되는 과정에서
일관성의 벽이 조금식 금이 가는 경우
  - 자연스러운 현상이며, 새로운 요구사항을 수용할 수 있는 협력 패턴을 향해 설계를 진화시킬 수 있는 좋은 신호로 받아 들일 것
  - 리팩토링!
  - 의지!
- 패턴
  - 변경의 방향을 파악할 수 있는 날카로운 감각
  - 다양한 캡슐화 방법과 설계 방법을 익히는 것
  - 패턴/프레임 워크... 
## 💡 느낀 점
- 처음에는 일관성을 유지하는 것처럼 보이던 협력 패턴이 시간이 흐르면서 새로운 요구사항이 추가 되는 과정에서 일관성의 벽이 조금식 금이 가는 경우에 대한 예시나 설명에서,
더 좋은 코드를 위해 나아가려는 의지가 중요한 것이지, 그렇게 못했다고 해서 무작정 나쁘다고 이야기할 것은 아니다.
- 디자인 패턴에 중요성을 다시 한번 느낀다.
- 캡슐화, 잘 수정할 수 있게, 수정하기 편하도록 설계한 방식 중에, 여러가지 패턴들이 반복적으로 사용되게 되고, 효율적으로 사용되었을 것이고, 그 여러가지 유용한 설계들을
디자인 패턴이라고 불리게 되었다라고 알고있다.
- 공식이라면 공식이겠지만, 굉장히 중요한 포인트인 듯.

## ❓ 궁금한 점
- if 문으로 처리 > 다형성으로처리
  - 파라미터로 type 을 넣어서 보내는가, 파라미터로 구현체를 선택해서 보내는가
  - 파라미터로 구현체를 선택해서 보내기 위해 type 으로 (service locator) 구현체를 찾아내야 하는 경우
  - type 별 컨트롤러 path 를 분리? front 에서 type 분리 필요...
  - 궁극적으로 type 을 정하는 로직을 맨 앞으로 꺼내는 일이라고 보면 되는걸까?

## 🔗 참고 링크
- 
