class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 ||t.length()==0) return "";
        int[] count_t=new int[128];
        for (char c :t.toCharArray()){
            count_t[c]++;
        }
        int req=0;
        for (int count : count_t){
            if (count > 0) req++;
        }
        int formed=0;
        int[] count_s= new int[128];
        int l=0,r=0;
        int minlen=Integer.MAX_VALUE;
        int head=0;
        while (r < s.length()){
            char ch =s.charAt(r);
            count_s[ch]++;
            if (count_t[ch] > 0 && count_s[ch] == count_t[ch]){
                formed++;
            }
            while (l <= r && formed == req){
                if (r-l+1 < minlen){
                   minlen=r-l+1;
                   head=l;
                }

                char left_char=s.charAt(l);
                count_s[left_char]--;
                if (count_t[left_char] > 0 && count_s[left_char] < count_t[left_char]){
                   formed--;
                }
                l++;
            } 
        r++;
        }
        return minlen == Integer.MAX_VALUE ? "" : s.substring(head,head+minlen);
    }
}