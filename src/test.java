public class test {
    public static void main(String[] args) {
        //掷骰子游戏 三次大于15 手气不错 10-15 手气一般 3-9 不怎么样
        int i=(int)(Math.random()*6+1);
        int j=(int)(Math.random()*6+1);
        int k=(int)(Math.random()*6+1);
        int sum=i+j+k;
        System.out.println("第一次掷骰子:"+i);
        System.out.println("第一次掷骰子:"+j);
        System.out.println("第一次掷骰子:"+k);
        if(sum>15) {
            System.out.println("手气不错");
        }
        else if(sum>=10&&sum<15) {
            System.out.println("手气一般");
        }
        else {
            System.out.println("手气太差");
        }
    }
}
