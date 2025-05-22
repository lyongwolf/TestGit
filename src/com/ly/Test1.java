package com.ly;

public class Test1 {

    public static void main(String[] args) {
        System.out.println("Hello World !!!");
        add();
    }

    public static void add() {
        int n = 10;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ans += i + j + k;
                }
            }
        }
        System.out.println(ans);
    }
}


class SegTree {
    private int[] sum;
    private int N;

    public SegTree(int len) {
        N = len;
        sum = new int[1 << (33 - Integer.numberOfLeadingZeros(N - 1))];
    }

    public void add(int o, int v) {
        add(o, v, 1, N, 1);
    }

    private void add(int o, int v, int l, int r, int i) {
        if (l == r) {
            sum[i] += v;
            return;
        }
        int m = (l + r) >> 1;
        if (o <= m) {
            add(o, v, l, m, i << 1);
        } else {
            add(o, v, m + 1, r, i << 1 | 1);
        }
        sum[i] = sum[i << 1] + sum[i << 1 | 1];
    }

    public int query(int l, int r, int i) {
        return query(l, r, 1, N, 1);
    }

    private int query(int L, int R, int l, int r, int i) {
        if (L <= l && r <= R) {
            return sum[i];
        }
        int m = (l + r) >> 1;
        int ans = 0;
        if (L <= m) {
            ans += query(L, R, l, m, i << 1);
        }
        if (R > m) {
            ans += query(L, R, m + 1, r, i << 1 | 1);
        }
        return ans;
    }
}