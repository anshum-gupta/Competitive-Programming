
public class edge implements Comparable<edge>{
        int to, from, weight;
        public edge(int x, int y, int z) {
            to = Math.min(x, y);
            from = Math.max(x,  y);
            weight = z;
        }
        public int compareTo(edge e) {
            return weight - e.weight;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + to;
            result = prime * result + from;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            edge cur = (edge)obj;
            if(to == cur.to && from == cur.from)return true;
            if(to == cur.from && from == cur.to)return true;
            return false;
        }
    }