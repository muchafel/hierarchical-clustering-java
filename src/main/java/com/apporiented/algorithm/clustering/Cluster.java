package com.apporiented.algorithm.clustering;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private String name;
	
	private Cluster parent;

	private List<Cluster> children;

	private Double distance;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public List<Cluster> getChildren() {
		if (children == null) {
			children = new ArrayList<Cluster>();
		}

		return children;
	}

	public void setChildren(List<Cluster> children) {
		this.children = children;
	}

	public Cluster getParent() {
		return parent;
	}

	public void setParent(Cluster parent) {
		this.parent = parent;
	}

	
	public Cluster(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addChild(Cluster cluster) {
		getChildren().add(cluster);

	}

	public boolean contains(Cluster cluster) {
		return getChildren().contains(cluster);
	}

	@Override
	public String toString() {
		return "Cluster " + name;
	}

	@Override
	public boolean equals(Object obj) {
		String otherName = obj != null ? obj.toString() : "";
		return toString().equals(otherName);
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public boolean isLeaf() {
		return getChildren().size() == 0;
	}
	
	public int countLeafs() {
	    return countLeafs(this, 0);
	}

    public int countLeafs(Cluster node, int count) {
        if (node.isLeaf()) count++;
        for (Cluster child : node.getChildren()) {
            count += child.countLeafs();
        }
        return count;
    }
    
    public void toConsole(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
            
        }
        String name = getName() + (isLeaf() ? " (leaf)" : "") + (distance != null ? "  distance: " + distance : "");
        System.out.println(name);
        for (Cluster child : getChildren()) {
            child.toConsole(indent + 1);
        }
    }

    public double getTotalDistance() {
        double dist = getDistance() == null ? 0 : getDistance();
        if (getChildren().size() > 0) {
            dist += children.get(0).getTotalDistance();
        }
        return dist;

    }
	   
}
