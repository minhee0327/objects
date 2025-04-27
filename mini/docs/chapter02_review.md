#  📝 Object Chapter 2 - ToC

1. DB 설계 먼저 → 도메인 설계를 평상시에 하게 되는 것 같은데, 어떤 게 맞는 거냐?
2. repository, service, controller 등도 객체로 볼 수 있는 거냐?
3. 상속 / 합성?
4. 디자인패턴 (유사해보인다고 한 패턴들 차이 정리) -> 각 패턴의 차이에 대해 논의
   1. TemplateMethod 패턴: skeleton + hook method 조합  /상속
   2. Strategy Pattern: 알고리즘을 다르게 쓰는 것 / 합성 
   3. Factory Method Pattern: 삼성키보드, LG 키보드  
   4. Abstract Method Pattern: 키보드, 마우스, ...
5. 상속 관련 문제 [링크](https://github.com/minhee0327/objects/blob/main/jino/week2/chapter2.md#%EC%83%81%EC%86%8D-%EB%AC%B8%EC%A0%9C)  
   1. 부모 - 자식 필드명 다르게 짓자.   
   2. 필드 은닉: 필드는 부모 - 자식 겹치면 선언한 부모 쪽으로 나온다.
   3. 메서드는 하위로 내려간다  
   4. 관련 문제 별첨

---



## 📘 1. DB 설계 먼저 vs 도메인 설계 먼저

### 💬 질문
> 평소에는 DB부터 설계하게 되는 경우가 많은데, 책에서는 도메인(객체) 설계를 먼저 하라고 한다.  
> 어떤 방식이 더 맞는 것이며, 실제 업무에서 어떻게 접근하는 게 좋은가?

### ✅ 정리

| 항목             | DB 설계 먼저             | 도메인 설계 먼저          |
|------------------|--------------------------|----------------------------|
| 관점             | 데이터 중심              | 행위 중심, 객체 협력 중심 |
| 접근 방식        | 어떤 데이터가 필요한가 → 테이블 설계 | 어떤 객체들이 어떤 메시지를 주고받는가 |
| 위험             | 비즈니스 로직이 테이블 제약에 종속됨 | 유연하게 확장 가능, 객체지향 원칙에 부합 |

> ✅ 도메인(객체) 설계를 먼저 하는 것이 객체지향적이며, 설계 유연성에 유리하다.  
> 특히 “협력 중심 설계”에서는 객체들 간의 책임과 메시지 흐름을 먼저 고려하는 것이 핵심이다.  
> DB는 이후에 도메인 모델을 반영해 정리하는 것이 좋다.

---



## 📘 2. repository, service, controller 등도 객체로 볼 수 있는 거냐?

1. 도메인 객체: 업무규칙, 비즈니스 의미를 표현하는 객체들

   1. 예: Order, Customer, Product, Money

2. 애플리케이션 객체: 도메인 객체를 **조립하거나 관리**하는, **인프라 혹은 전달용** 객체들

   1. 예: OrderService, OrderController, Order Repository
   2. 도메인 객체 조작, 흐름 제어

   | 구분                                  | 객체인가? | 어떤 종류?                             | 책임 중심                           | 예시                                |
   | ------------------------------------- | --------- | -------------------------------------- | ----------------------------------- | ----------------------------------- |
   | `Entity`, `VO`                        | ✅         | 도메인 객체                            | 비즈니스 로직                       | `Order`, `Money`, `Product`         |
   | `Controller`, `Service`, `Repository` | ✅         | 애플리케이션 객체 (조정자/전달자 역할) | 흐름 제어 / 요청 전달 / 인프라 처리 | `OrderService`, `ProductController` |

   > ✔ 즉, 모두 객체이긴 하지만 **목적과 책임이 다르다.**
   >  → 그래서 **협력 구조를 설계할 땐 도메인 객체 중심으로** 설계하고,
   >  → 애플리케이션 객체는 **그 협력을 지원**하는 역할을 한다고 보면 돼.



## 📘3. 상속(Inheritance) vs 합성(Composition)

### ✅ 핵심 비교

| 항목        | 상속 (Inheritance)             | 합성 (Composition)            |
|-------------|--------------------------------|-------------------------------|
| 정의        | 부모 클래스를 확장하여 기능을 재사용 | 객체를 필드로 포함시켜 기능을 위임 |
| 관계        | is-a                          | has-a                         |
| 재사용 방식 | 하위 클래스가 부모 기능 상속    | 객체 협력 구조로 조합         |
| 결합도      | 높음                           | 낮음                          |
| 유연성      | 낮음                           | 높음                          |
| 다중기능 조합 | 불가능 (단일 상속만 가능)     | 가능 (객체를 여러 개 합성 가능) |
| 대표 예     | `Dog extends Animal`           | `Car has Engine`              |

---

### 🧩 예시

#### 상속 예

```java
class Animal {
    void move() {
        System.out.println("움직인다");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("멍멍");
    }
}
```

#### 합성 예

```java
class Engine {
    void run() {
        System.out.println("엔진이 돌아감");
    }
}

class Car {
    private Engine engine = new Engine();

    void start() {
        engine.run();  // 기능 위임
    }
}
```

---

### ✅ 언제 어떤 걸 써야 할까?

| 상황                         | 상속 적합 | 합성 적합 |
|------------------------------|-----------|------------|
| 명확한 is-a 관계일 때        | ✅        | ❌         |
| 기능만 조합하고 싶을 때      | ❌        | ✅         |
| 여러 객체를 조합해야 할 때   | ❌        | ✅         |
| 유연하고 변경에 강한 설계가 필요할 때 | ❌        | ✅         |

---

> 🔥 **객체지향적 설계에서는 "상속보다 합성을 선호하라 (Favor Composition over Inheritance)"** 를 따르는 것이 원칙이다.



## 디자인 패턴

정리 PASS



## 상속 관련 문제

[링크1](상속 관련 문제)

```
public class Main {
    public static void main(String[] args) {
        A b = new B();
        b.paint(); 
        b.draw();   
    }
}

class A {
    public void paint() {
        System.out.print("A");
    }

    public void draw() {
        System.out.print("B");
        draw(); 
    }
}

class B extends A {
    @Override
    public void paint() {
        super.draw();          
        System.out.print("C");
        this.draw();          
    }

    @Override
    public void draw() {
        System.out.print("D"); 
    }
}

```

BDCDD

* super.draw() -> A.draw() 호출 -> B를 찍고 재귀에 걸리는게 아닌가 했는데, 호출한 b.draw의 this 를 가리키기 때문에, B -> D -> C -> D -> D
