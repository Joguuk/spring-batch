# StepScope, JobScope를 통해 Bean의 생성시점을 Job 혹은 Scope 실행시점으로 지연시키며 얻는 이점
1. JobParameter의 Late Binding이 가능하다.  
2. 동일한 컴포넌트를 병렬 혹은 동시에 사용할 때 유용한다.  
예를 들어 Step안에 Tasklet이 있고, 이 Tasklet이 멤버 변수와 이 멤버변수를 변경하는 로직이 있다고 가정해보자.  
   이 경우 @StepScope 없이 병렬로 실행시키게 되면 서로 다른 Step에서 하나의 Tasklet을 두고 마구잡이로 상태를 변경하려고 할 것이다.  
   하지만 @StepScope가 있다면 각각의 Step에서 별도의 Tasklet을 생성하고 관리하기 때문에 서로의 상태를 침범할 일이 없다.

# JobParameter를 사용해야 하는 이유
1. Spring Batch의 Job Parameter 관련 기능을 사용할 수 없다.  
예를 들어, Spring Batch는 같은 JobParameter로 같은 Job을 두 번 실행하지 않는데,
   시스템 변수를 사용하게 될 경우 이 기능이 동작하지 않는다.  
   또한, Spring Batch에서 자동으로 관리해주는 Parameter 관련 메타 테이블이 전혀 관리되지 않는다.
   
2. Command line이 아닌 다른 방법으로 Job을 실행하기가 어렵다.
3. Job Parameter를 사용하지 못하므로써 Late Binding이 불가능하다.

# Chunk
- Spring Batch에서 데이터 덩어리로 작업할 때 각 커밋 사이에 처리되는 row의 수
- 즉, Chunk 지향 처리란 한 번에 하나씩 데이터를 읽어 Chunk라는 덩어리를 만든 뒤, Chunk 단위로 트랜잭션을 다루는 것을 의미
- 따라서 실패할 경우엔 해당 Chunk까지만 롤백이 되고, 이전에 커밋된 트랜잭션 범위까지는 반영이 된다는 의미이다.
