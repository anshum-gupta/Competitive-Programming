

public class Pair<E, V> implements Comparable<Pair<E, V>>{
       E a;
       V b;
       public Pair(E x, V y) {a = x;b=y;}
       public int compareTo(Pair<E, V> p){
           return Integer.compare((Integer)a, (Integer)p.a);
       }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (Integer)a;
            result = prime * result + (Integer)b;
           
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            Pair<E, V> cur = (Pair<E, V>)obj;
            if((Integer)a == (Integer)cur.a && (Integer)b == (Integer)cur.b)return true;
            if((Integer)b == (Integer)cur.a && (Integer)a == (Integer)cur.b)return true;
            return false;
        }
 }