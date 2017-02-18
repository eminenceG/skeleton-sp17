public class NBody {
	static int lines;
	public static double readRadius(String file)
	{
		In in = new In(file);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file)
	{
		In in = new In(file);
		lines = in.readInt();
		double radius = in.readDouble();
		Planet[] planet = new Planet[lines];
		for(int i = 0; i < lines; i++)
		{
			planet[i] = new Planet(in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readDouble(), in.readDouble(), 
				in.readString());
		}	
		return planet;
	}

	public static void main(String[] args)
	{
		double T, dt, univRadius;
		double time = 0;
		String filename;
		Planet[] planets = new Planet[lines];

		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];
		univRadius = readRadius(filename);
		planets = readPlanets(filename);

		StdDraw.setScale(-1 * univRadius, univRadius);
		StdDraw.picture(0, 0,  "./images/starfield.jpg");

		for (Planet i : planets)
			i.draw();

		while(time < T)
		{
			double[] xForces = new double[lines];
			double[] yForces = new double[lines];
			for (int i = 0; i < lines; i++)
			{
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < lines; i++)
			{
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0,  "./images/starfield.jpg");
			for (Planet i : planets)
				i.draw();
			StdDraw.show(10);
			time += dt;
		}
		StdOut.printf("%d\n", lines);
		StdOut.printf("%.2e\n", univRadius);
		for (Planet i : planets) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   				i.xxPos, i.yyPos, i.xxVel, i.yyVel, i.mass, i.imgFileName);	
}		
	}
}
