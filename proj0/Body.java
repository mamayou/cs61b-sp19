public class Body {
	public double xxPos;  //Its current x position
	public double yyPos;
	public double xxVel;  // Its current velocity in the x direction
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;


	public Body(double xP, double yP, double xV, 
		        double yV, double m, String img) {
		xxPos = xP; yyPos = yP;
		xxVel = xV; yyVel = yV;
		mass = m; imgFileName = img;
	}

	public Body(Body b) {
		xxPos =b.xxPos; yyPos = b.yyPos;
		xxVel = b.xxVel; yyVel = b.yyVel;
		mass = b.mass; imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		return Math.sqrt(dx * dx + dy * dy);	
	}

	public double calcForceExertedBy(Body b) {
		double squareDistance = Math.pow(this.calcDistance(b), 2);
		return (G * this.mass * b.mass)/squareDistance;
	}

	public double calcForceExertedByX(Body b) {
		double dx = b.xxPos - this.xxPos;
		return dx * this.calcForceExertedBy(b)/this.calcDistance(b);
	}

	public double calcForceExertedByY(Body b) {
		double dy = b.yyPos - this.yyPos;
		return dy * this.calcForceExertedBy(b)/this.calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] arr) {
		double netForceX = 0;
		for(Body b : arr) {
			if (this.equals(b)) {
				netForceX +=0;
			} else {
				netForceX += this.calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] arr) {
		double netForceY = 0;
		for(Body b : arr) {
			if (this.equals(b)) {
				netForceY +=0;
			} else {
				netForceY += this.calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY) {
		double accelerationX = fX/this.mass;
		double accelerationY = fY/this.mass;
		this.xxVel += accelerationX * dt;
		this.yyVel += accelerationY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
		
		//StdDraw.pause(2000); 加上这句，五个图标会间隔两秒出现一个
	}
}