package hello.core.singleton;

public class SingletonService {

    //싱글톤 패턴을 하기 위해서는 아래의 코드를 다 작성해야 한다.

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    //자기 자신을 외부의 private로 가진다.
    private static final SingletonService instance = new SingletonService();

    //2. public 으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    //조회
    public static SingletonService getInstance(){
        //jvm이 뜰때 자신이 생성한 인스턴스를 참조를 넣어놓는다.
        return instance;
    }
    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
