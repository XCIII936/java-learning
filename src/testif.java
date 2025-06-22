public class testif {
    public static void main(String[] args) {
        double r=4*Math.random();
        double area=Math.PI*r*r;
        double circle=2*Math.PI*r;
        System.out.println("半径:"+r);
        System.out.println("面积"+area);
        System.out.println("周长"+circle);
        if(area>=circle){
            System.out.println("面积数值大于周长!");
        }else{
            System.out.println("面积数值小于周长!");
        }
    }
}
