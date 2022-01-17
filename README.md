# practice-with-Refactoring2.0

practice with Refactoring2.0 written by Martin Fowler<br>

## 리팩터링을 해야하는 이유 - 개인적인 생각

- 창업 경험에서 얻은 관점
  - 린 스타트업, 디자인 씽킹, 애자일 모두 공통점이 있다.
  - 가장 핵심이 되는 기능을 빠르게 만들어 피드백을 받는다.
  - 소비자의 반응을 지속적으로 반영하며 점진적으로 프로덕트를 만든다.
  - 이 과정에서 프로덕트는 수시로 변화한다. 이때, 꾸준한 리팩터링을 통해 깔끔한 코드, 시스템을 만들어 놨다면 변경이 쉬워진다.
  - 따라서 타이밍을 놓치지 않고 서비스, 프로덕트를 만들 수 있다.
  - 결국 소비자의 문제를 해결하는데 있어 리팩터링을 통해 더 빠르고 효율적으로 해결방안을 제시할 수 있는 토대가 될 수 있다.

## Chap 1. 첫번째 예시

리팩터링 2판의 예시는 자바스크립트로 이뤄져있다.<br>
코틀린에 익숙해지기 위해 코틀린으로 코드를 바꿔 연습한다.<br>
커밋 히스토리로 리팩터링 과정을 기록한다.<br>

### todo

리팩토링에 앞서 반드시 테스트를 준비한다.<br>

- kotlin을 위한 테스트 도구인 kotest를 이용하여 테스트 준비
- 결과 스트링을 비교하는 코드로 작성함

각 리팩토링 과정의 목적을 생각하며 진행한다.

### thinking & opinion

- 아주 유연한 자바스크립트를 코틀린으로 변환시키면서 생기는 문제점들이 있었다.
  - 이미 존재하는 객체에 새로운 프로퍼티를 추가하기
    - 해결방법: 임시 클래스를 만들면서 진행하거나, 기존 클래스에 프로퍼티를 추가함
    - 그러나 이렇게 진행하는 것이 올바른지 의문이 생김
- 클래스나, 함수로 추출하고 이전에 사용했던 변수, 함수를 추출한 클래스의 객체, 함수로 바꿀 때 꼼꼼하게 체크해야 한다
  - 실수할 수 있는 부분이라 반드시 테스트를 거쳐야 한다.
  - 테스트 코드를 통해 오류를 바로 발견할 수 있었다. 테스트야 고마워
- 테스트 코드 짜기가 생각보다 쉽지않다.
  - 리팩토링에 앞서 바꾸고자 하는 부분의 테스트를 마련해야하는데 아직 익숙하지 않다.
- 깃 커밋을 아주 잘게 쪼개는 연습이 되었다.
  - 코딩을 할 때, 구현에 급급하여 커밋을 수시로 하지 않았었다.
  - 실수가 발생했을 때 한참 전의 커밋으로 돌아가던가, 틀린 부분만 분석하여 다시 고쳤었다. 시간이 오래 걸렸다.
  - 단위 커밋이 작아지면 메시지 작성이 귀찮지만 결국 생산성이 더 좋아진다.


## Chap 2. 리팩터링 원칙 - .md only

> 정의
>
> 소프트웨어의 겉보기 동작*은 그래도 유지한 채, 코드를 이해하고 수정하기 쉽도록 내부 구조를 변경하는 기법 혹은 변경하기
>
> 겉보기 동작: 리팩터링 전과 후의 코드가 같은 결과 혹은 동작을 해야함

리팩터링은 정의에 따라 `특정 방식으로` 코드 구조를 바꾸는 것이다. 즉 겉보기 동작을 그대로 유지한 채, 작은 리팩터링 단계들을 연결하여 큰 변화를 이끌어 낸다.

- 리팩터링은 버그 픽스를 하지 않는다. 기능 추가도 하지 않는다.
- 리팩터링은 성능 개선이 목적이 아니다. 코드를 이해하고 수정하기 쉽게 바꾸는 것이 목적이다.

### 리팩터링하는 이유

- 소프트웨어 설계 개선
- 소프트웨어 이해 쉬워짐
  - 타인이 작성한 코드도 이해하기 쉬워지므로 협업에서 강한 강점
- 버그 찾기 쉬워짐
  - 리팩터링 과정을 통해 코드가 하는 일을 깊게 이해할 수 있고, 코드 구조를 더욱 명확히 다듬을 수 있다.
  - 코드가 명확해지면 버그는 지나칠 수 없다.
- 프로그래밍 속도 상승
  - 흔히 리팩토링을 하게되면 시간을 소모한다는 이미지가 있다.
  - 그러나 리팩토링을 통해 코드 구조가 명확해지면, 추후 기능을 추가할 때 훨씬 빨라진다.

### 언제 리팩터링 하는가?

1. 그냥 코딩한다.
2. 비슷한 일을 두 번째로 하게되어도 계속 진행한다.
3. 3 strike out. 리팩터링 하자!

### thinking & opinion

- 리팩터링은 따로 시간을 내서 하는 것이 아니다. 좋은 습관처럼 수시로 해야한다.
- 앞서 커밋을 잘게 나눠 메세지를 작성하는 것처럼 귀찮고 시간이 조금 더 소요되지만 결국 생산성이 좋아진다.
- 언제 리팩터링을 진행해야 하는지 판단하는 것은 아직 감이 안잡힌다.

## Chap 3. 리팩터링 대상 선정하기 - .md only

> 숙련된 개발자의 직관만큼 정확한 기준은 없다!!
>
> 각자 경험을 통해 감을 키워야 한다.

### 이상한 이름

코드를 명료하게 표현하는데 가장 중요한 요소 중 하나는 `이름`이다.<br>이름만 보고도 함수, 변수 등이 하는 일을 알 수 있어야 한다.<br>마땅한 이름이 떠오르지 않는다면 설계에 문제가 있을 가능성이 높다.

**리팩터링 기법**

- [함수 선언 바꾸기](#65)
- [변수 이름 바꾸기](#67)
- [필드 이름 바꾸기](#92)

### 중복 코드

같은 코드 구조가 반복된다면 통합하여 더 나은 프로그램을 만들 수 있다. (chap2의 3strike out)<br>다만 코드가 중복되는 부분에 서로 차이점이 존재하는지 꼼꼼하게 살펴보고 수정해야한다.

**리팩터링 기법**

- [함수 추출하기](#61)
- [문장 슬라이드하기](#86) 이후 함수 추출하기
- [메서드 올리기](#121)
  - 부모로부터 파생된 서브클래스에 코드 중복이 있다면 부모 클래스로 옮긴다.

### 긴 함수

함수가 길수록 이해하기 어렵다. 함수를 짧게 구성하면 코드를 이해, 공유, 선택하기 쉬워진다.<br>짧은 함수로 구성된 코드는 사람이 읽기에는 부담이 되지만, 명확한 `의도`를 드러내는 네이밍을 통해 이를 해결해야 한다.

**리팩터링 기법**

- [함수 추출하기](#61)
- [임시 변수를 질의 함수로 바꾸기](#74), [매개변수 객체 만들기](#68), [객체 통째로 넘기기](#11.4)
  - 너무 많은 매개변수, 임시 변수를 사용하면 함수 추출에 방해가 된다.
- [함수를 명령으로 바꾸기](#11.9)
  - 매개변수, 임시 변수를 제거했음에도 여전히 많다면 **함수를 명령**으로 바꾸는 방법을 고려해야한다.
- [조건문 분해하기](#10.1), [함수 추출하기](#61), [조건문을 다형성으로 바꾸기](#10.4)
  - 조건문은 추출 대상이 될 수 있다.
  - switch문의 case문 마다 함수를 추출한다.
  - 같은 기준으로 나누는 switch문이 많다면 **조건문을 다형성**으로 바꿔 적용한다.

- [반복문 쪼개기](#8.7)
  - 반복문도 추출 대상이 될 수 있다.
  - 반복문 내에 성격이 다른 작업이 있다면 **반복문 쪼개기**를 적용한다.

### 긴 매개변수 목록

과거에는 전역 변수 사용을 피하기위해 전부 매개변수로 전달하라고 했다(?)<br>그러나 매개변수가 많아지면 코드 이해가 어려워진다.

**리팩터링 기법**

- [임시 변수를 질의 함수로 바꾸기](#74), [매개변수 객체 만들기](#68), [객체 통째로 넘기기](#11.4)
- [플래그 인수 제거하기](#11.3)
  - 함수 동작방식을 결정하는 플래그 역할의 매개변수 제거
- [여러 함수를 클래스로 묶기](#69)
  - 여러 개의 함수가 특정 매개변수들의 값을 공통으로 사용할 때 공통 값들을 클래스 필드로 정의한다.
  - 함수형 프로그래밍이라면 [부분 적용 함수](#부분-적용-함수) 생성

### 전역 데이터

전역 데이터는 언제, 어디서나 접근 가능하고, 값이 변경 될 수 있다.<br>이는 버그의 원인이 될 가능성이 매우 높다.

**리팩터링 기법**

- [변수 캡슐화하기](#66)

### 가변 데이터

데이터를 변경하면 예상치 못한 결과나 버그가 발생할 수 있다. 코드의 다른 부분에서 해당 데이터를 참조한다는 점을 놓치면 발생한다.

함수형 프로그래밍에서 데이터는 불변이며 데이터를 변경하려면 복사본을 만들어 반환하는 개념을 갖는다.

**리팩터링 기법**

- [변수 캡슐화하기](#66)
  - 정해놓은 함수를 거쳐 값을 변경하면 데이터 변경 감시, 코드 개선에 용이하다.
- [변수 쪼개기](#9.1)
  - 하나의 변수가 다른 값들을 저장한다면 각 용도 별로 변수를 쪼개자.
- [문장 슬라이드하기](#86), [함수 추출하기](#61)
  - 갱신 로직을 별도로 분리하는게 좋다.
- [질의 함수와 변경 함수 분리하기](#11.1)
  - API를 만들 때 값을 읽는 함수와 값을 변경하는 함수는 분리한다.
- [세터 제거하기](#11.7)
- [파생 변수를 질의 함수로 바꾸기](#9.3)
- [여러 함수를 클래스로 묶기](#69), [여러 함수를 변환 함수로 묶기](#610)
  - 변수를 갱신하는 코드들의 유효범위를 제한한다.
- [참조를 값으로 바꾸기](#9.4)
  - 구조체처럼 내부 필드에 데이터를 담고 있는 변수라면, 내부 필드를 수정하지 말고 구조체를 통째로 교체한다.

### 뒤엉킨 변경

소프트웨어는 말 그대로 부드러워야 한다. 즉 수정에 용이해야 한다. 따라서 코드를 수정할 때는 딱 한 군데를 찾아 수정 가능한 시스템을 추구해야 한다.

단일 책임 원칙(SRP)이 제대로 지켜지지 않을 때 뒤엉킨 변경이 나타난다. 하나의 모듈이 어떤 이유들로 인해 계속 변경되는 일이 많아질 때 발생한다.

**리팩터링 기법**

- [단계 쪼개기](#611)
  - 일정한 순서에 의해 실행되는 것이 자연스럽다면, 다음 단계에 필요한 데이터를 구조에 담아 단계를 분리한다.
- [함수 옮기기](#8.1), [함수 추출하기](#61), [클래스 추출하기](#7.5)
  - 전체 처리 과정에서 각기 다른 맥락의 함수 호출이 많다면, 각 맥락에 적절한 모듈을 만들어 관리한다.
  - 여러 맥락에 관여하는 함수, 클래스가 있다면 추출을 먼저 한다.

### 산탄총 수술

산탄총 수술은 뒤엉킨 변경과는 반대로 코드를 변경할 때마다 자잘하게 수정해야하는 클래스가 많을 때이다. 변경할 부분이 코드 전반에 있다면 찾기가 어려워 놓칠 수 있다.

**리팩터링 기법**

- [함수 옮기기](#8.1), [필드 옮기기](#8.2), [여러 함수를 클래스로 묶기](#69), [여러 함수를 변환 함수로 묶기](#610)
  - 함께 변경되는 대상들을 묶어둔다.
  - 비슷한 데이터를 다루는 함수가 많다면 함수를 클래스로 묶는다.
  - 데이터 구조를 변환하거나 보강하는 함수들은 변환 함수로 묶는다.
- [단계 쪼개기](#611)
  - 앞에서 묶은 함수들의 출력 결과를 다음 단계로 넘길 수 있다면 단계를 나눌 수 있다.
- [함수 인라인하기](#62), [클래스 인라인하기](#7.6)
  - 애매하게 분리된 로직은 다시 함수, 클래스 인라인으로 합칠 수 있다.

### 기능 편애

프로그램 모듈화는 여러 영역으로 분할한 뒤, 각 영역 내에서 상호작용을 최대화하고 다른 영역 사이에서의 상호작용은 최소화해야 한다. 이 개념이 어긋날 때, 기능 편애가 나타난다.

**리팩터링 기법**

- [함수 옮기기](#8.1), [함수 추출하기](#61)
  - 외부 객체의 여러 게터 메서드를 호출 함수의 경우, 데이터 근처로 옮긴다.
  - 함수의 일부분에서 이런 성향이 나타나면 일부분을 함수로 추출하여 옮긴다.

### 데이터 뭉치

데이터 항목 서너 개가 여러 곳에서 같이 등장하는 경향이 많다. 이때, 몰려다니는 데이터 뭉치를 따로 추출해 줘야 한다. 데이터 뭉치인지 판별하려면 값 하나를 삭제해본다. 이때 나머지 데이터로는 의미가 없다면 객체로 분리 할 수 있다. 즉, 서로의 데이터가 어떤 로직에서 상호 의존적이라 할 수 있다.

**리팩터링 기법**

- [클래스 추출하기](#7.5), [매개변수 객체 만들기](#68), [객체 통째로 넘기기](#11.4)
  - 필드 형태의 데이터 뭉치를 찾아 클래스로 추출한다.
  - 메서드 시그니처*의 데이터 뭉치는 매개변수 객체로 만들거나, 객체를 통째로 넘겨 매개변수를 줄인다.

> 메서드 시그니처
>
> 메서드 명과 매개 변수 리스트의 조합. 이를 통해 각 함수를 구분할 수 있다.

### 기본형 집착

프로그래밍 언어에서 제공하는 기본형 사용을 선호하는 사람이 많다. 자신의 문제에 맞는 객체(화폐, 좌표, 구간 등)로 바꾸자. 특히 문자열을 다루는 코드에서 많이 일어난다.

**리팩터링 기법**

- [기본형을 객체로 바꾸기](#7.3)
  - 의미있는 자료형으로 구성한다.
- [타입 코드를 서브클래스로 바꾸기](#12.6), [조건부 로직을 다형성으로 바꾸기](#10.4)
  - 기본형 코드가 조건부 동작을 제어하는 타입 코드라면 서브 클래스로 바꿔 다형성으로 조건부 로직을 제어한다.
- [클래스 추출하기](#7.5), [매개변수 객체 만들기](#68)
  - 기본형 데이터 뭉치도 추출한다.

### 반복되는 switch 문

중복된 switch 문이 문제가 되는 이유는 조건절을 추가할 때 마다, 나머지 switch 문도 수정을 해야하기 때문이다. 따라서 중복된 switch, if-else 문에 집중한다.

**리팩터링 기법**

- [조건부 로직을 다형성으로 바꾸기](#10.4)

### 반복문

현재 여러 프로그래밍 언어에서 반복문을 대체하는 함수들이 생겨서 반복문을 사용하지 않아도 되는 상황이 많다. 자바의 경우 스트림, 코틀린의 경우 컬렉션 함수 등

**리팩터링 기법**

- [반복문을 파이프라인으로 바꾸기](#8.8)

### 성의 없는 요소

코드 구조를 결정할 때, 프로그래밍 언어가 제공하는 요소를 이용하는 걸 선호한다. 그러나 언어에서 지원하는 요소가 필요 없을 때도 있다. 함수가 하나 있는 클래스 등 빈약한 요소 같은 경우 리팩터링으로 삭제한다.

**리팩터링 기법**

- [함수 인라인하기](#62), [클래스 인라인하기](#7.6)
- [계층 합치기](#12.9)
  - 상속을 사용한 경우

### 추측성 일반화

YAGNI에 어긋나는 지금 당장 필요없는 코드는 바로 삭제한다.

**리팩터링 기법**

- [계층 합치기](#12.9)
  - 역할이 거의 없는 추상클래스는 삭제한다.
- [함수 인라인하기](#62), [클래스 인라인하기](#7.6)
  - 쓸데없이 위임하는 코드는 삭제한다.
- [함수 선언 바꾸기](#65)
  - 본문에서 사용하지 않는 매개변수, 한번도 사용한 적 없는 매개 변수는 삭제한다.
- [죽은 코드 제거하기](#8.9)
  - 테스트에서나 사용하는 코드는 테스트 케이스와 함께 삭제한다.

### 임시 필드

가끔 특정 상황에서 값이 할당되는 필드를 가진 클래스가 존재한다. 그러나 객체를 가져와 사용할 땐, 당연히 모든 필드가 채워져 있을 것이라 예상한다. 따라서 임시 필드를 갖도록 코드를 작성하면 이해하기 어렵다.

**리팩터링 기법**

- [클래스 추출하기](#7.5), [함수 옮기기](#8.1)
  - 임시 필드들이 있을 경우, 클래스로 추출하고 해당 필드를 다루는 함수는 새 클래스로 옮긴다.
- [특이 케이스 추가하기](#10.5)
  - 임시 필드 유효성 조건부 로직은 대안클래스를 만들어 제거할 수 있다.

### 메세지 체인

클라이언트가 한 객체를 통해 다른 객체를 요청하는 과정을 연쇄적으로 진행할 때, 긴 메세지 체인이 생성된다. 이는 클라이언트가 객체 내비게이션 구조에 종속*됨을 의미한다. 따라서 내비게이션 중간 단계를 수정하면 클라이언트도 코드를 수정해야한다.

[예시]

```kotlin
val managerName = aPerson.department.manager.name //어떤 사람이 속해있는 부서의 매니저 이름을 반환

매니저 이름을 반환하는 게터 함수는 사람, 부서, 매니저 등 어느 곳에서든 추가할 수 있다.
즉 체인을 구성하는 모든 객체에 위임 숨기기 적용이 가능하다.

val managerName = aPerson.department.managerName //manager 숨김
val managerName = aPerson.manager.name // department 숨김
val managerName = aPerson.managerName //manager, department 숨김

결국 사용하는 값은 매니저 이름이므로 aPerson을 전달받아 매니저 이름을 사용하는 함수를 만들 수 있다.
```

> 객체 내비게이션 구조에 종속
>
> 위 예시에서 클라이언트가 어떤 사람이 속한 부서의 매니저 이름을 알고 싶을 때, 긴 메시지 체인을 통해 접근한다. 이때 aPerson, department, manager 중 하나라도 코드가 바뀐다면 managerName을 사용하려는 클라이언트의 모든 코드는 수정돼야 한다. 따라서 클라이언트는 객체 내비게이션 구조에 종속되었다고 볼 수 있다.

**리팩터링 기법**

- [위임 숨기기](#7.7)
  - 메시지 체인의 모든 객체에 적용할 수 있지만 중간 객체 모두 중개자*가 되기 쉽다.
- [함수 추출하기](#61), [함수 옮기기](#8.1)
  - 최종 결과 객체를 사용하는 코드를 빼낸 후, 메세지 체인을 숨길 수 있는지 확인한다.

### 중개자

캡슐화하는 과정에서 위임이 자주 활용된다. 그러나 위임이 지나치면 문제가 된다. 어떤 클래스의 메서드의 절반 이상이 다른 클래스에 위임한다면, 그 클래스는 껍데기일 뿐이다. 사장 클래스의 대부분의 일을 비서 클래스가 담당한다면 사장 클래스의 메서드를 사용하는 것이 아니라 바로 비서 클래스의 메서드를 사용한다.

**리팩터링 기법**

- [중개자 제거하기](#7.8), ([함수 인라인 하기](#62))
  - 실제로 일을 하는 객체와 직접 소통하게 한다.
  - 위임 메서드를 제거한 후 남는 일이 거의 없다면 호출하는 쪽으로 인라인 한다.

### 내부자 거래

객체지향에서는 응집도를 높히고, 결합도를 낮추려고 한다. 각 모듈간의 결합이 아예 존재하지 않을 수는 없다. 그러나 모듈간의 결합은 최대한 줄이고 투명하게 처리해야 한다.

**리팩터링 기법**

- [함수 옮기기](#8.1), [필드 옮기기](#8.2)
  - 결합도가 높은 모듈들은 서로 떼어놓아야 한다.
- [위임 숨기기](#7.7)
  - 여러 모듈의 공통 관심사를 처리하는 모듈을 만들거나 위임 숨기기로 다른 모듈이 중간자 역할을 하게 만든다.
- [서브 클래스를 위임으로 바꾸기](#12.10), [슈퍼클래스를 위임으로 바꾸기](#12.11)
  - 상속 구조에서는 부모 자식간의 결합이 생길 때 사용한다.

### 거대한 클래스

하나의 클래스에 기능이 집중되면 필드 수가 상당히 늘어난다. 필드가 많으면 중복 코드가 생기기 쉽다. 또한 코드량이 너무 많은 클래스도 중복 코드와 혼동을 야기할 여지가 많다.

**리팩터링 기법**

- [클래스 추출하기](#7.5), [슈퍼클래스 추출하기](#12.8), [타입 코드를 서브클래스로 바꾸기](#12.6)
  - 보통 한 클래스 안에서 접두어, 접미어가 같은 필드들을 추출한다.
  - 분리할 컴포넌트를 슈퍼클래스, 서브클래스로 적용할 수도 있다.
  - 클라이언트들이 거대한 클래스를 이용하는 패턴을 파악하여 기능별로 클래스를 쪼갤 수 있다.

### 서로 다른 인터페이스의 대안 클래스들

자유로운 클래스 교체는 클래스를 사용할 때 큰 장점이다. 단, 교체할 클래스들의 인터페이스가 같아야 한다.

**리팩터링 기법**

- [함수 선언 바꾸기](#65), [함수 옮기기](#8.1)
  - 메서드 시그니처를 동일하게 바꾼다.
  - 함수 옮기기로 인터페이스가 같아질 때까지 메서드들을 클래스 안으로 넣는다.
- [슈퍼클래스 추출하기](#12.8)
  - 대안 클래스들 사이에 중복 코드가 생기면 슈퍼클래스로 추출할 수 있다.

### 데이터 클래스

데이터 필드와 게터 세터로 이뤄진 클래스이다. 데이터 저장용도(주로 DTO)로 사용되다 보니 다른 클래스에서 데이터 클래스를 함부로 다룬다. 따라서 적절한 캡슐화가 필요하다.

**리팩터링 기법**

- [레코드 캡슐화 하기](#7.1)
  - public 필드를 캡슐화 한다.
- [세터 제거하기](#11.7)
  - 불변 필드는 세터를 제거한다. 그러나 코틀린에서는 val을 사용하면 된다.
- [함수 옮기기](#8.1), [함수 추출하기](#61)
  - 게터 세터를 직접 사용하는 클래스의 메서드는 데이터 클래스 안으로 옮겨질 수 있다. (사각형의 면적을 직접 구하는 것 -> area를 받는 것)
  - 메서드를 통째로 옮기기 어려우면 일부분만 추출해서 옮길 수 있다.

### 상속 포기

부모의 일부분의 기능만 자식 클래스에서 사용하고 싶을 때, 상속을 포기하고 싶다. '예전'에는 동일 계층에 클래스를 하나 만들고 필요없는 부모 클래스의 기능을 모두 새로운 클래스로 옮긴다. 보통의 경우, 상속을 포기하는 대신 이 방식을 따른다. 부모 동작은 필요로 하지만 부모의 인터페이스를 따르고 싶지 않을 때 상속 메커니즘에서 벗어날 수 있다.

**리팩터링 기법**

- [메서드 내리기](#12.4), [필드 내리기](#12.5)
  - 따르지 않을 부모 클래스의 기능을 형제 클래스로 옮긴다.
- [서브클래스를 위임으로 바꾸기](#12.10), [슈퍼클래스를 위임으로 바꾸기](#12.11)
  - 클래스 자체를 위임으로 바꿔 상속에서 벗어날 수 있다.

### 주석

주석은 보통 코드 가독성에 도움을 준다. 하지만 구조가 좋지 않아 모든 설명을 주석으로 때우려고 할 수 있다. 이때 구조만 바꾼다면 주석을 지울 수 있다.

**리팩터링 기법**

- [함수 추출하기](#61)
  - 특정 코드 블록이 하는 일에 주석을 달고 싶다면 추출하여 함수의 기능을 명확하게 한다.
- [함수 선언하기](#65)
  - 위의 리팩터링을 거쳤음에도 주석이 필요하다면 함수 이름을 명확하게 한다.
- [어서션 추가하기](#10.6)
  - 시스템이 동작하기 위한 선행 조건이 필요하다면 어서션을 추가한다.

### thinking & opinion

- 일부 케이스는 챕터 1 실습을 통해 명확하게 알 수 있었다.
- 챕터 6부터 시작하는 리팩터링 기법의 자세한 내용을 아직 알지 못해 각 상황과 기법 매칭이 아직 이해가 안된다. 따라서 보기 쉽게 링크로 관리한다.
- 리팩터링 문제를 먼저 정의하고, 그에 대한 해결책을 정리한다. 즉 챕터 순서와는 별개로 정리한다.
- 어떤 함수, 변수, 클래스를 추출하고 위치를 옮기는 것만으로도 매우 다른 결과가 나온다.

## chap 6. 기본적인 리팩터링

### 6.1

함수 추출하기 <---> [함수 인라인하기](#62)

**배경**

목적과 구현을 분리하는 방식이 가장 합리적으로 생각된다. 코드를 보고 하는 일을 파악하는데 시간이 걸린다면 함수를 추출하여 알맞은 이름을 정한다. (이름짓기가 제일 어렵다..)

**절차**

1. 함수를 새로 만들고 `목적`을 잘 나타내는 이름 짓기 (how 보다 what에 집중)
2. 추출할 코드를 원본 함수에서 복사하여 새 함수로 이동
3. 추출한 코드 중 지역변수 참조, 유효범위를 체크한다. 있으면 매개변수로 전달
4. 변수, 유효범위를 처리한 뒤 컴파일
5. 원본함수에서 추출한 함수를 호출하는 코드로 변경
6. 테스트
7. 추출한 함수와 같거나 비슷한 코드가 있는지 체크, 있다면 추출 함수를 호출하도록 바꿀지 검토

**예시**

**유효범위를 벗어나는 변수가 없을 때**

```kotlin
// 원본 code
fun printOwing(invoice: Invoice) {
  var outstanding = 0
  
  println("**************")
  println("***고객 채무***")
  println("**************")
  
  // 미해결 채무(outstading)을 계산
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  // 마감일(dueDate)을 기록
  val today = Clock.today // 시스템 시간을 사용하지 않고 별도의 wrapper를 만들어 사용하면 테스트 결과를 일정하게 할 수 있다.
  invoice.dueDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 30)
  
  // 세부 사항 출력
  println("고객명: ${invoice.customer}")
  println("채무액: ${outstanding}")
  println("마감일: ${invoice.dueDate.toLocalDateString()}")
}
```

```kotlin
// println 추출
fun printOwing(invoice: Invoice) {
  var outstanding = 0
  
  printBanner() // <-- 배너 출력 추출
  
  // 미해결 채무(outstading)을 계산
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  // 마감일(dueDate)을 기록
  val today = Clock.today
  invoice.dueDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 30)
  
  fun printDetails() { // <-- nested method로 추출하여 invoice에 접근 가능
  	println("고객명: ${invoice.customer}")
  	println("채무액: ${outstanding}")
	  println("마감일: ${invoice.dueDate.toLocalDateString()}")  
  }
  
  printDetails()
}

fun printBanner() {
  println("**************")
  println("***고객 채무***")
  println("**************")
}
```

**지역 변수를 사용할 때**

지역 변수를 그대로 사용할 때, 매개변수로 그냥 넘기자.

```kotlin
// printDetail() 밖으로 추출
fun printOwing(invoice: Invoice) {
  var outstanding = 0
  
  printBanner()
  
  // 미해결 채무(outstading)을 계산
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  // 마감일(dueDate)을 기록
  val today = Clock.today
  invoice.dueDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 30)
    
  printDetails(invoice, outstanding)
}

fun(invoice: Invoice, outstanding: Int) { // <-- 지역 변수를 매개변수로 받음
  println("고객명: ${invoice.customer}")
  println("채무액: ${outstanding}")
	println("마감일: ${invoice.dueDate.toLocalDateString()}")
}

fun printBanner() {
  println("**************")
  println("***고객 채무***")
  println("**************")
}
```

```kotlin
// invoice date 할당 로직도 추출
fun printOwing(invoice: Invoice) {
  var outstanding = 0
  
  printBanner()
  
  // 미해결 채무(outstading)을 계산
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  recordDueDate(invoice) // <-- 마감일 설정 함수 추출, invoice 매개변수로 전달
  printDetails(invoice, outstanding)
}

fun recordDueDate(invoice: Invoice) {
  val today = Clock.today
  invoice.dueDate = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 30)
}

fun(invoice: Invoice, outstanding: Int) { // <-- 지역 변수를 매개변수로 받음
  println("고객명: ${invoice.customer}")
  println("채무액: ${outstanding}")
	println("마감일: ${invoice.dueDate.toLocalDateString()}")
}

fun printBanner() {
  println("**************")
  println("***고객 채무***")
  println("**************")
}
```

**지역 변수의 값을 변경할 때**

지역변수의 값을 변경하는 코드를 발견한다면 [변수 쪼개기](#91)로 임시 변수를 만들고 그 변수에 대입한다. 임시 변수는 크게 2가지로 나눌 수 있다.

- 추출 함수안에서만 사용됨
- 함수 밖에서 사용됨

```kotlin
// 1. 변수 선언을 로직과 가까운 데로 옮김
fun printOwing(invoice: Invoice) {
  printBanner()
  
  // 미해결 채무(outstading)을 계산
  var outstanding = 0 // <-- 로직 근처로 이동
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  recordDueDate(invoice)
  printDetails(invoice, outstanding)
}
```

```kotlin
// 2. 추출 부분을 새 함수로 복사
fun printOwing(invoice: Invoice) {
  printBanner()
  
  // 미해결 채무(outstading)을 계산
  var outstanding = 0
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  
  recordDueDate(invoice)
  printDetails(invoice, outstanding)
}

fun calculateOutstanding(invoice: Invoice): Int {
  var outstanding = 0
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  return ourstanding // <-- 수정된 값 반환
}
```

```kotlin
// 3. outstanding 선언까지 함수로 추출하여 매개변수로 전달할 필요 없음
// 4. 컴파일
// 5. 추출한 코드의 반환 값을 원래 변수에 저장
// 6. 코드 변수명 바꾸기
fun printOwing(invoice: Invoice) {
  printBanner()
  
  // 미해결 채무(outstading)을 계산
  val outstanding = calculateOutstanding() // <-- 5. 반환 값 불변으로 저장
  recordDueDate(invoice)
  printDetails(invoice, outstanding)
}

fun calculateOutstanding(invoice: Invoice): Int {
  var result = 0 // <-- 6. 변수명 바꾸기
  for(i in invoice.orders) {
    outstanding += i.amout
  }
  return result
}
```

> 반환할 변수가 여러 개라면?
>
> - 각각의 변수를 하나씩 반환하는 함수들로 쪼개기 추천
> - 레코드로 묶어 반환할 수도 있지만, 임시 변수를 [질의 함수로 바꾸기](#74) 적용 혹은 [변수 쪼개기](#91) 적용
> - 원래 함수의 스코프 밖으로 추출해보면 코드를 제대로 추출했는지 알 수 있다.

### 6.5

함수 선언 바꾸기

**배경**

함수는 레고 블럭과 같다고 생각한다. 함수는 소프트웨어 시스템의 구성요소를 조립하는 연결부이다. 따라서 연결부가 잘 정의되어 있다면 새로운 부분을 추가하기가 쉬워진다. 다행이 소프트웨어는 말그대로 소프트하기 때문에 연결부를 수정할 수 있다.

연결부에서 가장 중요한 것은 이름이다. 코드 그 자체로 함수의 목적을 설명할 수 있어 가독성이 좋아지고 코드 이해가 쉬워진다.

> 이름짓기 Tip: 주석으로 함수의 목적을 설명해보면 알맞은 이름을 찾을 수 있다.

함수의 매개변수 또한 중요하다. 매개변수는 함수를 사용하는 문맥(상황)을 설정한다. 매개변수를 어떻게 설정하느냐에 따라 함수 활용의 범위도 달라진다. 적절한 매개변수 선택은 다른 모듈과의 결합을 지울 수 있다. 그러나 적절한 매개변수 선택에는 정답이 없고 시스템 구성의 변화, 코드 로직에 대한 이해에 따라 유연하게 대처해야한다.

**절차**

간단한 절차 - 단번에 수정

1. 매개변수를 제거하려면 함수 본문에서 해당 매개변수를 사용하는지 확인한다.
2. 메서드 선언을 바꾼다.
3. 기존 메서드를 참조하는 부분을 모두 찾아 바꾼다. (IDE 기능 사용 추천)
4. 테스트

이름과 매개변수 모두 바꾸고 싶다면 각각 독립적으로 처리하고 문제가 생기면 마이그레이션 절차로 넘어간다.

마이그레이션 절차 - 점진적 수정

1. 함수의 본문을 적절히 리팩터링한다. 하지만 적절히가 가장 어렵다.
2. 본문을 새로운 함수로 추출한다.
3. 추출한 함수에 매개변수를 추가해야한다면 `간단한 절차`를 따라 추가한다.
4. 테스트
5. 기존 함수를 인라인한다.
6. 이름을 임시로 붙여두었으면 원래 이름으로 수정한다.
7. 테스트

**예시**

**함수 이름 바꾸기 - 간단한 절차**

```kotlin
// 원본 code
fun circum(radius: Double): Double {
  return 2 * Math.PI * radius
}
```

```kotlin
fun circumference(radius: Double): Double { // <-- 이름 변경
  return 2 * Math.PI * radius
}
```

**함수 이름 바꾸기  - 마이그레이션 절차**

```kotlin
fun circum(radius: Double): Double { // <-- 원래 함수는 놔두고 리팩터링한 함수를 리턴한다.
  return circumference(radius)
}

fun circumference(radius: Double): Double {
  return 2 * Math.PI * radius
}
```

함수 선언 바꾸기는 공개된 API를 리팩터링 하기에 좋다. 기존 함수의 껍데기는 살려두고 내부만 바꿔 적용 가능하기 때문이다. 기존 함수는 deprecated를 고지하고 모든 클라이언트가 새로운 함수로 마이그레이션하면 이전 함수는 지울 수 있다.(게으른 클라이언트로 인해 지우지 못 할 수도 있다.)

**매개변수 추가하기 - 마이그레이션 절차** 

```kotlin
// 원본 code
fun addReservation(customer: Customer) {
  this._reservations.add(customer)
}
```

요구사항: 우선순위 큐에 customer를 넣어야 한다.

```kotlin
// 2. 새로운 함수로 추출
fun addReservation(customer: Customer) {
  this.zz_addReservation(customer) // <-- 임시 이름
}

fun zz_addReservation(customer: Customer) {
  this._resulvations.add(customer)
}
```

```kotlin
// 3. 매개변수 추가
fun addReservation(customer: Customer) {
  this.zz_addReservation(customer, false)
}

fun zz_addReservation(customer: Customer, isPriority: Boolean) {
  this._resulvations.add(customer)
}
```

**매개변수를 속성으로 바꾸기**

```kotlin
//원본 code 고객이 NewEngland 주에 살고 있는지 판단하는 함수
fun inNewEngland(aCustomer: Customer): Boolean {
  return listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(aCustomer.address.state)
}
```

```kotlin
// 1. 변수 추출
// 2. 함수 추출
fun inNewEngland(aCustomer: Customer): Boolean {
 val stateCode = aCustomer.address.state // <-- 1. 변수 추출
  return xxNewInNewEngland(stateCode)
}

fun xxNewInNewEngland(stateCode: String): Boolean { // <-- 2. 함수 추출, 임시 이름, 매개변수 변경
  return listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(stateCode)
}
```

```kotlin
fun inNewEngland(aCustomer: Customer): Boolean {
  return xxNewInNewEngland(aCustomer.address.state) // <-- 추출한 임시 변수 인라인
}
```

```Kotlin
// 함수 호출문
val newEnglanders = somCustomers.filter{ c -> xxNewInNewEngland(c.address.state)} // <-- 함수 인라인

val newEnglanders = somCustomers.filter{ c -> inNewEngland(c.address.state)} // <-- 함수 이름 변경

fun inNewEngland(stateCode: String): Boolean { // <-- 함수 이름 변경
  return listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(stateCode)
}
```

### 6.7

변수 이름 바꾸기

**배경**

변수명을 처음부터 잘 지으면 좋겠지만 그렇지 않은 경우가 더 많을 것이다. 결국 더욱 적절한 변수명으로 고쳐야 할 경우가 발생한다. 특히 값을 계속 사용하는 필드의 경우 더욱 신중하게 이름을 지어야 한다.

**절차**

1. 폭넓게 쓰이는 변수는 [변수 캡슐화하기](#66)를 고려한다.
2. 이름을 바꿀 변수를 참조하는 곳을 모두 찾아 `하나씩` 변경한다.
   - 다른 코드 베이스에서도 참조하는 변수라면 이름을 바꿀 수 없다.
3. 테스트

**예시**

함수 밖에서도 참조 가능한 변수 이름을 바꾸는 예시 (이 외에는 너무 간단하다.)

```kotlin
// 원본 code
var tpHd = "untitled"

// 변수를 읽기만하는 코드
var result += "<h1>${tpHd}</h1>"

// 변수를 수정하는 코드
tpHd = "anotherString"
```

```kotlin
// 1. 변수 캡슐화 하기
fun title(): String = tpHd
fun setTitle(arg: String) {
  tpHd = arg
}

setTitle("anotherString")
result += "<h1>${title()}</h1>"

// 변수명 수정

var _title = "untitled" // <-- 변수명 수정

fun title(): String = _title // <-- 수정된 변수명 적용
fun setTitle(arg: String) {
  _title = arg // <-- 수정된 변수명 적용
}
```

## chap 7. 캡슐화

### 7.4

임시 변수를 질의 함수로 바꾸기

**배경**

어떤 함수를 통해 얻은 결괏값을 다시 참조하기 위해 임시 변수로 사용하는 경우가 많다. 임시 변수를 사용하면 코드가 반복되는 것을 줄일 수 있고, 변수 이름으로 코드 이해가 쉬워지기도 한다. 그러나 임시 변수보다는 함수로 만들어 사용하는 것이 나을 때가 많다.

**절차**

1. 변수가 사용되기 전에 값이 확실히 결정되는지, 변수를 사용할 때마다 매번 다른 결과를 내지 않는지 확인한다.
2. 읽기 전용으로 만들 수 있는 변수는 읽기 전용으로 만든다.
3. 테스트
4. 변수 대입문을 함수로 추출한다.
   - 변수와 함수가 같은 이름을 가질 수 없으면 일단 임시 이름을 짓는다.
   - 추출한 함수가 다른 부수 효과를 일으키는지 확인한다.
   - 부수효과가 있다면 [질의 함수와 변경 함수 분리하기](#111)로 대처한다.
5. 테스트
6. [변수 인라인하기](#64)로 임시 변수를 제거한다.

**예시**

```kotlin
class Order(quantity: Int, item: Item) {
	fun getPrice(): Double {
    var basePrice = quantity * item.price // 임시 변수
    var discountFactor = 0.98D // 임시 변수
    
    if(basePrice > 1000) discountFactor -= 0.03
    return basePrice * discountFactor
  }
}
```

```kotlin
class Order(quantity: Int, item: Item) {
	fun getPrice(): Double {
    val basePrice = quantity * item.price // 읽기 전용으로 바꾼 뒤 테스트, 만약 이 변수를 바꾸려고 하면 컴파일 에러
    var discountFactor = 0.98D
    
    if(basePrice > 1000) discountFactor -= 0.03
    return basePrice * discountFactor
  }
}
```

```kotlin
class Order(quantity: Int, item: Item) {
	fun getPrice(): Double {
    val basePrice = getBasePrice() // <-- 추출한 함수 대입
    var discountFactor = 0.98D
    
    if(basePrice > 1000) discountFactor -= 0.03
    return basePrice * discountFactor
  }
  
  fun getBasePrice(): Int { // 함수로 추출
    return quantity * item.price
  }
}
```

``` kotlin
class Order(quantity: Int, item: Item) {
	fun getPrice(): Double {
    //val basePrice = getBasePrice()     basePrice를 사용하는 부분에 getBasePrice()로 대체
    
    //if(getBasePrice() > 1000) discountFactor -= 0.03
    return getBasePrice() * getDiscountFactor() // discountFactor를 함수로 대체
  }
  
  fun getBasePrice(): Int {
    return quantity * item.price
  }
  
  fun getDiscountFactor() { // 함수로 추출
    var discountFactor = 0.98D
    if(getBasePrice() > 1000) discountFactor -= 0.03
    return getBasePrice() * discountFactor
  }
}
```

## chap 8. 기능 이동

### 8.6

문장 슬라이드하기

**배경**

관련된 코드들을 서로 가까이에 두면 코드를 이해하기 수월하다. 문장 슬라이드를 통해 관련된 코드를 모아둘 수 있다. 문장 슬라이드하기는 보통 [함수 추출하기](#61)에 앞서 선행된다

**절차**

1. 코드 조각을 옮길 목표 위치를 체크한다. 다음 상황이라면 이 리팩터링은 할 수 없다.
   - 옮길 코드에서 참조하는 요소를 선언하는 문장 앞으로 이동 불가
   - 옮길 코드를 참조하는 요소의 뒤로 이동 불가
   - 옮길 코드에서 참조하는 요소를 수정하는 문장 건너 뛰기 불가
   - 옮길 코드가 수정하는 요소를 참조하는 요소를 건너 뛰기 불가
2. 코드 조각을 잘라 목표 위치로 옮긴다.
3. 테스트

**예시**

코드를 슬라이드 시키려면 무엇을 슬라이드할지, 슬라이드가 가능한지 체크한다. 슬라이드할 코드 자체와 그 코드가 건너 뛰어야할 코드 모두 살펴야한다. 코드의 순서가 바뀌면 동작이 달라지는지 체크한다.

```kotlin
val pricingPlan = retrievePricingPlan()
val order = retreiveOrder() // <-- order를 처음 사용하는 val units = .. 위로 이동
val baseCharge = pricingPlan.base
var charge = 0 
val chargePerUnit = pricingPlan.unit
val units = order.units
var discount = 0 // <-- discount를 실제로 사용하는 discount = .. 위로 이동
charge = baseCharge + units * chargePerUnit
var discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0)
discount = discountable * pricingPlan.discountFactor
if(order.isRepeat) discount += 20
charge = charge - discount
chargeOrder(charge)
```

order에 할당하는 retreiveOrder() 코드 내부도 살펴봐야 한다. 그러나 질의 함수를 잘 만들어 놨다면 대부분 코드 이동이 가능하다.

항시 테스트를 통해 리팩터링이 가능한지 결과를 보아야 한다. 테스트 커버리지가 넓지 않다면 더욱 신중히 리팩터링한다. 테스트를 실패했다면 더 작게 슬라이드 해보거나 덜 위험한 부분까지 슬라이드 해본다.

**예시: 조건문이 있을 때 슬라이드**

조건문 밖으로 슬라이드하면 중복 로직이 제거될 것이고 조건문 안으로 슬라이드하면 로직이 중복될 것이다.

```kotlin
var result = null
if(availableResources.length == 0) {
  result = createResource()
  allocatedResouces.push(result!!) // 중복로직
} else {
  result = availableResources.pop()
  allocatedResources.push(result!!) // 중복로직
}
return result
```

```kotlin
var result = null
if(availableResources.length == 0) {
  result = createResource()
} else {
  result = availableResources.pop()
}
allocatedResources.push(result!!) // 조건문 밖으로 슬라이드하여 중복로직이 합쳐짐
return result
```

## chap 9. 데이터 조직화

### 9.2

필드 이름 바꾸기

**배경**

프로그램 곳곳에 쓰이는 레코드 구조체의 필드 이름은 매우 중요하다. 데어터 구조는 코드를 이해하는데 핵심 역할을 한다. 따라서 레코드의 필드 이름을 더욱 알맞게 바꿔야 한다. 또한 클래스에서 게터 세터의 이름은 필드와 다를바 없다. 게터 세터의 이름도 필드와 마찬가지로 중요하다.

**절차**

1. 레코드의 유효 범위가 제한적이라면 필드에 접근하는 모든 코드를 수정 후 테스트한다. 다음 절차는 필요 없다.
2. 레코드가 캡슐화 되어 있지 않다면 [캡슐화](#71)한다.
3. 캡슐화된 객체 내부의 private 필드명을 바꾸고 그에 맞게 내부 메서드 변경한다.
4. 테스트
5. 생성자의 매개변수와 필드가 겹치면 [함수 선언 바꾸기](#65)로 변경한다.
6. 접근자들의 이름도 [변경](#65)한다.

**예시**

자바스크립트의 익명 객체를 코틀린으로 표현하기 어렵다. dynamic이 있지만 jvm을 위한 타입이 아니다. 따라서 data class부터 시작한다.

```kotlin
//원본 코드 js
const organization = {name: "애크미 구스베리", country: "GB"};

data class Organization(val data: Person) {
  var _name: String = data.name
  // 자바로 바뀌면
   @NotNull
   private final String _name;
   @NotNull
   private final String _country;

   @NotNull
   public final String get_name() {
      return this._name;
   }

   @NotNull
   public final String get_country() {
      return this._country;
   }
// 자바 끝
  var _country: String = data.country
}
```

```kotlin
//필드 이름 바꾸기
data class Organization(val data: Person) {
  private var _title: String = data.name // <-- 필드 이름 변경
  private var _country: String = data.country
}
```

kotlin은 타입 언어이므로 필드 이름은 변경하면 이미 컴파일 타임에 에러가 생긴다. 따라서 js 보다 훨씬 휴먼에러를 줄일 수 있다.


## chap 12. 상속 다루기

### 12.1

메서드 올리기 <---> [메서드 내리기](#124)

**배경**

중복코드는 한쪽의 변경이 다른 한쪽에는 반영되지 않을 수 있는 상황을 만들 수 있다. 하지만 중복코드를 찾기는 쉽지 않다.

메서드 올리기를 적용하려면 선행 단계를 거쳐야 할 때가 많다. 다른 클래스의 [메서드를 매개변수화](#112) 하면 같은 메서드가 되기도 한다. 그 후 메서드를 상속 계층의 위로 올린다. 반면 메서드 올리기를 적용하기 가장 복잡한 상황은 메서드 본문에서 서브클래스의 필드를 참조하는 상황이다. 이럴 땐, 먼저 [필드를 슈퍼클래스로 올리고](#122) 메서드를 올린다.

**절차**

1. 똑같이 동작하는 메서드인지 확인, 일은 같지만 코드가 다르다면 같아질 때까지 리팩터링한다.
2. 메서드 안에서 호출하는 다른 메서드와 참조하는 필드들을 슈퍼클래스에서도 호출, 참조가 가능한지 확인한다.
3. [함수 선언 바꾸기](#65)로 메서드 시그니처를 동일하게 만든다.
4. 슈퍼클래스에 새로운 메서드를 생성하고, 대상 메서드 본문을 넣는다.
5. 정적 검사 수행
6. 서브클래스 중 하나의 메서드를 제거한다.
7. 테스트
8. 모든 서브클래스에 6, 7과정을 반복한다.

**예시**

```kotlin
class Employee : Party() {
  val monthlyCost = 10
  fun annualCost() : Int = monthlyCost * 12
}

class Department : Party() {
  val monthlyCost = 20
  fun totalAnnualCost() : Int = monthlyCost * 12
}
```

두 클래스에서 같은 메서드를 찾았다. 두 메서드의 이름이 다르므로 [함수 선언 바꾸기](#65)로 이름을 통일한다.

서브클래스 중 하나의 이름을 복사해 슈퍼클래스에 넣는다. 이때 코틀린은 정적 언어이므로 슈퍼 클래스는 open 키워드를 갖고 있고, open function이 아니면 컴파일 시 에러가 난다. 따라서 한번에 서브 클래스의 메서드를 바꿔주어야 한다.

```kotlin
open class Party {
  val monthlyCost = 10
  fun annualCost(): Int = monthlyCost * 12 // <-- 슈퍼클래스로 open 키워드 없이 올리면 서브클래스에선 override 불가
}
class Employee : Party() {
  val monthlyCost = 10
  fun annualCost() : Int = monthlyCost * 12 // 따라서 에러 발생
}

class Department : Party() {
  override val monthlyCost = 20
  fun annualCost() : Int = monthlyCost * 12 // <-- 함수 이름 맞추기
}
```
