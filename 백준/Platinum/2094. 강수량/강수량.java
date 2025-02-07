import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] segmentTree;
    public static int base;
    public static List<Integer> yearList = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        base = 1;
        while(base < N) {
            base <<= 1;
        }

        segmentTree = new int[base * 2];

        Map<Integer, Integer> map = new HashMap<>();

        int index = base;

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int year = Integer.parseInt(st.nextToken());
            int rain = Integer.parseInt(st.nextToken());

            map.put(year, index);
            yearList.add(year);

            segmentTree[index] = rain;

            index++;
        }

        makeMaxSegmentTree();

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int Yyear = Integer.parseInt(st.nextToken());
            int Xyear = Integer.parseInt(st.nextToken());

            int Yidx, Xidx;

            boolean YnotFoundFlag = false;
            boolean XnotFoundFlag = false;

            if (!map.containsKey(Yyear)) {
                YnotFoundFlag = true;
                Yidx = getLeftIndex(Yyear);
            } else {
                Yidx = map.get(Yyear);
            }

            if (!map.containsKey(Xyear)) {
                // 둘다 년도가 없을 경우는 maybe
                if (YnotFoundFlag) {
                    sb.append("maybe").append("\n");
                    continue;
                }
                XnotFoundFlag = true;
                Xidx = getRightIndex(Xyear);
            } else {
                Xidx = map.get(Xyear);
            }

            if (!YnotFoundFlag && !XnotFoundFlag && segmentTree[Yidx] < segmentTree[Xidx]) {
                sb.append("false").append("\n");
                continue;
            }
            // 끝값이 있을떄, 끝값을 제외한 나머지가 끝값보다 작아야함
            if (!XnotFoundFlag && !YnotFoundFlag) {
                // 시작값이 있을 떄, 시작값을 제외해야함
                if (!isValid(Yidx + 1, Xidx - 1, Xidx)) {
                    sb.append("false").append("\n");
                    continue;
                }
            } else if (!XnotFoundFlag) {
                // 시작값이 없을 때, 시작값을 포함해야함
                if (!isValid(Yidx, Xidx - 1, Xidx)) {
                    sb.append("false").append("\n");
                    continue;
                }
            } else {
                // 끝값이 없고, 시작값이 있을 때
                if (!isValid(Yidx + 1, Xidx, Yidx)) {
                    sb.append("false").append("\n");
                    continue;
                }
            }
            if ((Xidx - Yidx) != (Xyear - Yyear)) sb.append("maybe").append("\n");
            else sb.append("true").append("\n");
        }
        System.out.print(sb);
    }

    public static void makeMaxSegmentTree() {
        for(int i = base-1; i>0; i--) {
            segmentTree[i] = Math.max(segmentTree[i*2], segmentTree[i*2 + 1]);
        }
    }

    public static boolean isValid(int stIdx, int edIdx, int targetIdx) {
        if(stIdx > edIdx) return true;

        int st = stIdx;
        int ed = edIdx;
        int target = segmentTree[targetIdx];

        int max = 0;

        while(st <= ed) {
            if(st % 2 == 1) {
                max = Math.max(max, segmentTree[st]);
                st++;
            }
            if(ed % 2 == 0) {
                max = Math.max(max, segmentTree[ed]);
                ed--;
            }
            st /= 2;
            ed /= 2;
        }

        return max < target;
    }

    // stYear 보다 큰 값중 가장 작은
    public static int getLeftIndex(int stYear) {
        int st = 0;
        int ed = yearList.size();

        while(st < ed) {
            int mid = (st + ed) / 2;

            if(stYear < yearList.get(mid)) {
                ed = mid;
            }
            else {
                st = mid + 1;
            }
        }
        return st + base;
    }

    // edYear 보다 작은 값중 가장 큰
    public static int getRightIndex(int edYear) {
        int st = 0;
        int ed = yearList.size()-1;

        while(st < ed) {
            int mid = (st + ed + 1) / 2;

            if(edYear < yearList.get(mid)) {
                ed = mid-1;
            }
            else {
                st = mid;
            }
        }
        return ed + base;
    }
}
