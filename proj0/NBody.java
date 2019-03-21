public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		double universeRadius = in.readDouble();
		return universeRadius;
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double universeRadius = in.readDouble();
		Body[] arr = new Body[numberOfPlanets];
		for (int i = 0; i < numberOfPlanets; i += 1) {
			/**
			在对类数组进行初始化后，数组的元素也就是类，也必须用
			new来为其分配空间，不然其值全为null，进行赋值操作会报错。
			*/
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			arr[i] = new Body(xxPos, yyPos, xxVel,
							  yyVel, mass, imgFileName);
		}
		return arr;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];
		double universeRadius = NBody.readRadius(filename);

		Body[] arr = NBody.readBodies(filename);
		for(int i = 0; i < arr.length; i +=1) {
			arr[i].imgFileName = "images/" + arr[i].imgFileName;
		}

		String imageToDraw = "images/starfield.jpg";

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-1.0 * universeRadius, universeRadius);
		//StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		for(Body b : arr) {
			b.draw();
		}
		StdDraw.show();
		StdDraw.pause(10);

		// animation
		double time = 0.0;
		while (time <= T) {
			double[] xForces = new double[arr.length];
			double[] yForces = new double[arr.length];
			for (int i = 0; i < arr.length; i +=1) {
				xForces[i] = arr[i].calcNetForceExertedByX(arr);
				yForces[i] = arr[i].calcNetForceExertedByY(arr);
			}
			for (int i = 0; i < arr.length; i += 1) {
				arr[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, imageToDraw);
			for(Body b : arr) {
			b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		System.out.printf("%d\n", arr.length);
		System.out.printf("%.2e\n", universeRadius);
		for (Body b : arr) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							  b.xxPos, b.yyPos, b.xxVel,
							  b.yyVel, b.mass, b.imgFileName);
		}
	}
}