public class Driver{
  public static void main(String[] args){
    KnightBoard test = new KnightBoard(5,5);
    System.out.println(test);
    //System.out.println(test.solve(0,0));
    //System.out.println(test);
    System.out.println(test.countSolutions(0,0));
    System.out.println(test);
  }
}
