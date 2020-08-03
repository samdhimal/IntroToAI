public class Node implements Comparable{

    private int row, col;
    private int fCost, heuristic;
    private int totalCost;
    private int visit;
    private Node parent;

    public Node(int r, int c, int visit){
        row = r;
        col = c;
        parent = null;
        //type 0 is traverseable, 1 is not
        this.visit = visit;
    }

    //mutator methods to set values
    public void setF(int f){
        this.fCost = f;
        totalCost = fCost + heuristic;
    }
    public void setH(int h){
        this.heuristic = h;
        totalCost = fCost + heuristic;
    }
    public void setParent(Node parent){
        this.parent = parent;
    }

    //accessor methods to get values
    public int getF(){
        return fCost;
    }
    public int getH(){
        return heuristic;
    }
    public Node getParent(){
        return parent;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getVisit() {
        return visit;
    }
    public int getFCost(){
        return totalCost;
    }

    public boolean equals(Object temp){
        //typecast to Node
        Node n = (Node) temp;

        return row == n.getRow() && col == n.getCol();
    }

    public String toString(){
        return "Node: " + row + "_" + col;
    }

    @Override
    public int compareTo(Object temp) {
        int value;
        Node node = (Node) temp;
        if (this.getFCost() > node.getFCost()) {
            value = 1;
        }
        else if (this.getFCost() == node.getFCost()) {
            value = 0;
        }
        else {
            value = -1;
        }
        return value;
    }

}
