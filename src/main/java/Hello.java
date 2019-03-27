import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // run game
            startGame(sc);

            System.out.printf("게임을 새로 시작하려면, 1, 종료하려면 2를 입력하세요.");
            int num = sc.nextInt();
            System.out.printf("num: %d\n", num);

            if (num == 2) break;
        }
        System.out.println("종료합니다.");
    }

    public static void startGame(Scanner sc) {
        // init answer
        int []opBallPnts = genRandomBallPnts();

        System.out.println(Arrays.toString(opBallPnts));

        while (true) {
            System.out.printf("숫자를 입력해주세요 : ");
            int num = sc.nextInt();

            // check num is valid


            int []myBallPnts = genBallPnts(num);
            System.out.println(Arrays.toString(calcCounter(opBallPnts, myBallPnts)));
        }
    }

    public static int[] genBallPnts(int num) {
        int [] pnts = {0, 0, 0};

        for(int i = 0; i < 3; i++) {
            pnts[2 - i] = num % 10;
            num /= 10;
        }

        return pnts;
    }


    public static int[] genRandomBallPnts() {
        int [] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Random rand = new Random();

        // suffle
        for(int from = 0; from < nums.length; from++) {
            int to = rand.nextInt(nums.length);
            int tmp = nums[from];
            nums[from] = nums[to];
            nums[to] = tmp;
        }

        System.out.printf("len: %d\n", Arrays.copyOfRange(nums, 0, 3).length);

        return Arrays.copyOfRange(nums, 0, 3);
    }

    public static int[] calcCounter(int[] opBallPnts, int[] myBallPnts) {
        System.out.printf("opBallPnts: %s\n", Arrays.toString(opBallPnts));
        System.out.printf("myBallPnts: %s\n", Arrays.toString(myBallPnts));

        int[] counter = {0, 0}; // {strike cnt, ball cnt}

        int[] existPnts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        // check strike
        for(int i = 0; i < opBallPnts.length; i++) {
            int opBallPnt = opBallPnts[i];
            if (opBallPnts[i] == myBallPnts[i]) {
                counter[0]++;
                continue;
            }
            existPnts[opBallPnt] = 1;
        }

        // check ball
        // for(int i = 0; i < myBallPnts.length; i++) {
        //     int pnt = myBallPnts[i];
        for(int pnt : myBallPnts) {
            if (existPnts[pnt] == 1) counter[1]++;
        }

        System.out.println(Arrays.toString(counter));

        return counter;
    }
}
