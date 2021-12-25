# practice-with-Refactoring2.0

practice with Refactoring2.0 written by Martin Fowler<br>
각 챕터별 브랜치의 리퀘스트를 통해 커밋 이력을 볼 수 있다.

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

- [함수 선언 바꾸기](#6.5)
- [변수 이름 바꾸기](#6.7)
- [필드 이름 바꾸기](#9.2)

### 중복 코드

같은 코드 구조가 반복된다면 통합하여 더 나은 프로그램을 만들 수 있다. (chap2의 3strike out)<br>다만 코드가 중복되는 부분에 서로 차이점이 존재하는지 꼼꼼하게 살펴보고 수정해야한다.

**리팩터링 기법**

- [함수 추출하기](#6.1)
- [문장 슬라이드하기](#8.6) 이후 함수 추출하기
- [메서드 올리기](#12.1)
  - 부모로부터 파생된 서브클래스에 코드 중복이 있다면 부모 클래스로 옮긴다.

### 긴 함수

함수가 길수록 이해하기 어렵다. 함수를 짧게 구성하면 코드를 이해, 공유, 선택하기 쉬워진다.<br>짧은 함수로 구성된 코드는 사람이 읽기에는 부담이 되지만, 명확한 `의도`를 드러내는 네이밍을 통해 이를 해결해야 한다.

**리팩터링 기법**

- [함수 추출하기](#6.1)
- [임시 변수를 질의 함수로 바꾸기](#7.4), [매개변수 객체 만들기](#6.8), [객체 통째로 넘기기](#11.4)
  - 너무 많은 매개변수, 임시 변수를 사용하면 함수 추출에 방해가 된다.
- [함수를 명령으로 바꾸기](#11.9)
  - 매개변수, 임시 변수를 제거했음에도 여전히 많다면 **함수를 명령**으로 바꾸는 방법을 고려해야한다.
- [조건문 분해하기](#10.1), [함수 추출하기](#6.1), [조건문을 다형성으로 바꾸기](#10.4)
  - 조건문은 추출 대상이 될 수 있다.
  - switch문의 case문 마다 함수를 추출한다.
  - 같은 기준으로 나누는 switch문이 많다면 **조건문을 다형성**으로 바꿔 적용한다.

- [반복문 쪼개기](#8.7)
  - 반복문도 추출 대상이 될 수 있다.
  - 반복문 내에 성격이 다른 작업이 있다면 **반복문 쪼개기**를 적용한다.

### 긴 매개변수 목록

과거에는 전역 변수 사용을 피하기위해 전부 매개변수로 전달하라고 했다(?)<br>그러나 매개변수가 많아지면 코드 이해가 어려워진다.

**리팩터링 기법**

- [임시 변수를 질의 함수로 바꾸기](#7.4), [매개변수 객체 만들기](#6.8), [객체 통째로 넘기기](#11.4)
- [플래그 인수 제거하기](#11.3)
  - 함수 동작방식을 결정하는 플래그 역할의 매개변수 제거
- [여러 함수를 클래스로 묶기](#6.9)
  - 여러 개의 함수가 특정 매개변수들의 값을 공통으로 사용할 때 공통 값들을 클래스 필드로 정의한다.
  - 함수형 프로그래밍이라면 [부분 적용 함수](#부분-적용-함수) 생성

### 전역 데이터

전역 데이터는 언제, 어디서나 접근 가능하고, 값이 변경 될 수 있다.<br>이는 버그의 원인이 될 가능성이 매우 높다.

**리팩터링 기법**

- [변수 캡슐화하기](#6.6)

### 가변 데이터

데이터를 변경하면 예상치 못한 결과나 버그가 발생할 수 있다. 코드의 다른 부분에서 해당 데이터를 참조한다는 점을 놓치면 발생한다.

함수형 프로그래밍에서 데이터는 불변이며 데이터를 변경하려면 복사본을 만들어 반환하는 개념을 갖는다.

**리팩터링 기법**

- [변수 캡슐화하기](#6.6)
  - 정해놓은 함수를 거쳐 값을 변경하면 데이터 변경 감시, 코드 개선에 용이하다.
- [변수 쪼개기](#9.1)
  - 하나의 변수가 다른 값들을 저장한다면 각 용도 별로 변수를 쪼개자.
- [문장 슬라이드하기](#8.6), [함수 추출하기](#6.1)
  - 갱신 로직을 별도로 분리하는게 좋다.
- [질의 함수와 변경 함수 분리하기](#11.1)
  - API를 만들 때 값을 읽는 함수와 값을 변경하는 함수는 분리한다.
- [세터 제거하기](#11.7)
- [파생 변수를 질의 함수로 바꾸기](#9.3)
- [여러 함수를 클래스로 묶기](#6.9), [여러 함수를 변환 함수로 묶기](#6.10)
  - 변수를 갱신하는 코드들의 유효범위를 제한한다.
- [참조를 값으로 바꾸기](#9.4)
  - 구조체처럼 내부 필드에 데이터를 담고 있는 변수라면, 내부 필드를 수정하지 말고 구조체를 통째로 교체한다.

### 뒤엉킨 변경

소프트웨어는 말 그대로 부드러워야 한다. 즉 수정에 용이해야 한다. 따라서 코드를 수정할 때는 딱 한 군데를 찾아 수정 가능한 시스템을 추구해야 한다.

단일 책임 원칙(SRP)이 제대로 지켜지지 않을 때 뒤엉킨 변경이 나타난다. 하나의 모듈이 어떤 이유들로 인해 계속 변경되는 일이 많아질 때 발생한다.

**리팩터링 기법**

- [단계 쪼개기](#6.11)
  - 일정한 순서에 의해 실행되는 것이 자연스럽다면, 다음 단계에 필요한 데이터를 구조에 담아 단계를 분리한다.
- [함수 옮기기](#8.1), [함수 추출하기](#6.1), [클래스 추출하기](#7.5)
  - 전체 처리 과정에서 각기 다른 맥락의 함수 호출이 많다면, 각 맥락에 적절한 모듈을 만들어 관리한다.
  - 여러 맥락에 관여하는 함수, 클래스가 있다면 추출을 먼저 한다.

### 산탄총 수술

산탄총 수술은 뒤엉킨 변경과는 반대로 코드를 변경할 때마다 자잘하게 수정해야하는 클래스가 많을 때이다. 변경할 부분이 코드 전반에 있다면 찾기가 어려워 놓칠 수 있다.

**리팩터링 기법**

- [함수 옮기기](#8.1), [필드 옮기기](#8.2), [여러 함수를 클래스로 묶기](#6.9), [여러 함수를 변환 함수로 묶기](#6.10)
  - 함께 변경되는 대상들을 묶어둔다.
  - 비슷한 데이터를 다루는 함수가 많다면 함수를 클래스로 묶는다.
  - 데이터 구조를 변환하거나 보강하는 함수들은 변환 함수로 묶는다.
- [단계 쪼개기](#6.11)
  - 앞에서 묶은 함수들의 출력 결과를 다음 단계로 넘길 수 있다면 단계를 나눌 수 있다.
- [함수 인라인하기](#6.2), [클래스 인라인하기](#7.6)
  - 애매하게 분리된 로직은 다시 함수, 클래스 인라인으로 합칠 수 있다.

### thinking & opinion

- 일부 케이스는 챕터 1 실습을 통해 명확하게 알 수 있었다.
- 챕터 6부터 시작하는 리팩터링 기법의 자세한 내용을 아직 알지 못해 각 상황과 기법 매칭이 아직 이해가 안된다. 따라서 보기 쉽게 링크로 관리한다.
- 리팩터링 문제를 먼저 정의하고, 그에 대한 해결책을 정리한다. 즉 챕터 순서와는 별개로 정리한다.
- 어떤 함수, 변수, 클래스를 추출하고 위치를 옮기는 것만으로도 매우 다른 결과가 나온다.
