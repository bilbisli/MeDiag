package internals;

import java.io.Serializable;

public class Range implements Serializable {

	private static final long serialVersionUID = 1L;
	double start = 0, end;

	public Range(double end) {
		this.end = end;
	}

	public Range(double start, double end) {
		this.start = start;
		this.end = end;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}
	
	public boolean isInRange(double value) {
		return (getDeviation(value) == 0);
	}
	
	public double getDeviation(double value) {
		if (value < start)
			return value - start;
		else if(value > end)
			return value - end;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Range(" + start + ", " + end + ")";
	}
}
