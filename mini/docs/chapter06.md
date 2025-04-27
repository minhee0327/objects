# Chapter6 > 메시지와 인터페이스

## 📌 학습 내용 요약

협력과 메시지 용어정리

- 메세지: 객체 사이의 요청 (ex: 예매해줘, 계산해줘)
- 오퍼레이션: 메시지를 처리할 객체의 동작 (reserve, calcurateFee)
- 메서드: 메시지가 실제 런타임에 동작하는거
- 메서드 시그니처: 오퍼레이션을 코드로 정의한 것 (public Reservation reserve(..))
- 퍼블릭 인터페이스: 외부에서 접근 가능한 메서드 집합

메시지 먼저 결정하고, 그 메시지를 처리할 오퍼레이션과 메서드 시그니처가 따라오도록하자.



인터페이스 설계 품질에 영향을 미치는 원칙 (4가지)

1. 디미터 법칙: 최소 지식 원칙 → 객체는 가까운 이웃하고 협력

2. 묻지 말고 시켜라: 데이터를 꺼내서 내가 직접 처리할게 아니라, 객체에게 시켜라1

   ```java
   //Bad Example
   if (shape.getType() == ShapeType.RECTANGLE) {
       // 사각형 그리기 로직
   } else if (shape.getType() == ShapeType.CIRCLE) {
       // 원 그리기 로직
   }
   
   //Good
   shape.draw();
   ```

3. 의도를 정확히 드러내는 인터페이스: 메서드 명을 명확하게 지을것 (책임에 맞게, 의도가 드러나게) 예시 1 처럼

   - 예시1
     - DiscountCondition.isSatisfiedBy
     - PeriodCondition.isSatisfiedBy,
     - SequenceCondition.isSatisfiedBy

   - 예시 2처럼 setTicket → Audience는 buy, TicketSeller는 sellTo, Bag은 hold

4. Command - Query 패턴 : 반환 없이 내용을 변경 (Command, 명령) : 결과물 반환만 내용변경 x(Query, 조회) 둘을 명확하게 구분할 것



## 💡 느낀 점

- 



## ❓ 궁금한 점

그럼 Command(Upsert / Delete) 시 반환해주는 것도 위반한건가?

→ GPT 대답

## 비교

### 🟢 괜찮은 설계 (위반 아님)

```java
public UserResponseDto updateUserName(Long id, String newName) {
    User updatedUser = userRepository.findById(id);
    updatedUser.changeName(newName);
    return UserResponseDto.from(updatedUser);
}
```

- 여기서 updateUserName는 **Command**야 (상태 변경 O)

- 하지만 결과로 **"변경된 상태가 뭔지 알려주는 것"**은

  → "변경 결과 보고"일 뿐이지 **조회(Query)를 섞은 게 아님**.

그래서 **CQS 위반 아님**

(실제 DDD, CQRS 패턴도 이렇게 많이 씀)

### ❌ 나쁜 설계 (위반)

```java
public boolean updateUserNameIfUserIsActive(Long id, String newName) {
    if (userRepository.findById(id).isActive()) {   // 조회
        userRepository.findById(id).changeName(newName); // 변경
        return true;
    }
    return false;
}
```

→ 변경과 조회 논리가 **한 메서드 안에 같이 들어가 있어서 혼동됨**.