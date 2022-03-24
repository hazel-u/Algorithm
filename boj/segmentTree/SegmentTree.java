package boj.segmentTree;

public class SegmentTree {
    long tree[];
    int treeSize;

    public SegmentTree(int arrSize) {
        /*
            Tree의 높이는 전체 배열 개수를 log화 한 것
            Leaf는 n개이고, 전체 개수는 각 leaf를 더한 개수도 포함해야하므로, log(n)의 높이로 구해야함 (소수점이 있어 더 필요하니 ceil사용)
            Java의 log는 자연로그 함수이므로 log(2)를 통해 나누어 log2로 나누는 효과를 구함
         */
        int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));

        // Tree에 들어가는 Node의 개수는 2의 높이+1 제곱의 미만개이다.
        this.treeSize = (int) Math.pow(2,h+1);
        tree = new long[treeSize]; // 합을 저장하기 때문에 long으로 하는게 좋을듯
    }

    public long init(int[] nums, int node_idx, int start, int end) {
        // 만약 start == end라면 leaf노드라는 의미
        // 즉, 배열의 값을 그대로 저장함
        if(start==end) {
            return tree[node_idx] = nums[start];
        }

        // 위에서 return못했다면 start!=end
        // 그렇다면 좌측 노드와 우측 노드의 합으로 구해짐
        // 좌측 노드의 idx는 *2이며 우측 노드의 idx는 *2+1이 된다.
        return tree[node_idx] = init(nums, node_idx*2, start, (start+end)/2)
                + init(nums, node_idx*2+1, (start+end)/2+1, end);
    }

    public void update(int node_idx, int start, int end, int idx, long diff) {
        // 만약 변경할 index 값이 범위 밖이면 해당 tree는 확인 불필요
        if(idx<start || end<idx) return;

        // 변경된 차이만큼을 영향받는 각 node에 더해줌
        tree[node_idx] += diff;

        // leaf노드에 다다르기까지 모든 노드의 값을 바꿔야하므로 지속 진행
        if(start != end) {
            update(node_idx*2, start, (start+end)/2, idx, diff); //좌측 node
            update(node_idx*2+1, (start+end)/2+1, end, idx, diff); // 우측 node
        }
    }

    // 구하고자 하는 덧셈을 진행
    public long sum(int node_idx, int start, int end, int left, int right) {
        // 범위를 벗어나게 되는 경우 더할 필요 없음
        if(left>end || right<start) {
            return 0;
        }

        // 범위 내 완전히 포함 시에는 더 내려가지 않고 return
        if(left<=start && end<=right) {
            return tree[node_idx];
        }

        // 그 외의 경우 좌 / 우측으로 지속 탐색 수행
        return sum(node_idx*2, start, (start+end)/2, left, right)
                + sum(node_idx*2+1, (start+end)/2+1, end, left, right);
    }
}
