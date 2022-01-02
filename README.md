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
- [필드 이름 바꾸기](#9.2)

### 중복 코드

같은 코드 구조가 반복된다면 통합하여 더 나은 프로그램을 만들 수 있다. (chap2의 3strike out)<br>다만 코드가 중복되는 부분에 서로 차이점이 존재하는지 꼼꼼하게 살펴보고 수정해야한다.

**리팩터링 기법**

- [함수 추출하기](#61)
- [문장 슬라이드하기](#8.6) 이후 함수 추출하기
- [메서드 올리기](#12.1)
  - 부모로부터 파생된 서브클래스에 코드 중복이 있다면 부모 클래스로 옮긴다.

### 긴 함수

함수가 길수록 이해하기 어렵다. 함수를 짧게 구성하면 코드를 이해, 공유, 선택하기 쉬워진다.<br>짧은 함수로 구성된 코드는 사람이 읽기에는 부담이 되지만, 명확한 `의도`를 드러내는 네이밍을 통해 이를 해결해야 한다.

**리팩터링 기법**

- [함수 추출하기](#61)
- [임시 변수를 질의 함수로 바꾸기](#7.4), [매개변수 객체 만들기](#68), [객체 통째로 넘기기](#11.4)
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

- [임시 변수를 질의 함수로 바꾸기](#7.4), [매개변수 객체 만들기](#68), [객체 통째로 넘기기](#11.4)
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
- [문장 슬라이드하기](#8.6), [함수 추출하기](#61)
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
