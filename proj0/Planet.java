public class Planet 
{
	double xxPos; 
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,
	 			  double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p)
	{
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Planet p)
	{
		double G = 6.67e-11f;
		double F = G * mass * p.mass / (calcDistance(p) * calcDistance(p));
		return F;
	}

	public double calcForceExertedByX(Planet p)
	{
		double fx;
		fx = calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p)
	{
		double fy;
		fy = calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] p)
	{
		double fNetX = 0;
		for (Planet i : p)
		{
			if (equals(i))
				continue;
			fNetX = fNetX + calcForceExertedByX(i);
		}
		return fNetX;
	}

	public double calcNetForceExertedByY(Planet[] p)
	{
		double fNetY = 0;
		for (Planet i : p)
		{
			if (equals(i))
				continue;
			fNetY = fNetY + calcForceExertedByY(i);
		}
		return fNetY;
	}

	public boolean equals(Planet p)
	{
		return (xxPos == p.xxPos && yyPos == p.yyPos);
	}

	public void update(double dt, double fX, double fY)
	{
		double aX, aY;

		aX = fX / mass;
		aY = fY / mass;
		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}

	public static void main(String[] args)
	{
		Planet Samh = new Planet(1, 0, 0, 0, 10, "x");
		Planet AEgir = new Planet(3, 3, 0, 0, 5, "x");
		Planet Rocinante = new Planet(5, -3, 0, 0, 50, "x");

		double Fnetx, Fnety;
		Fnetx = Samh.calcForceExertedByX(AEgir) + Samh.calcForceExertedByX(Rocinante);
		Fnety = Samh.calcForceExertedByY(AEgir) + Samh.calcForceExertedByY(Rocinante);

		System.out.println("Fnetx is " + Fnetx);
		System.out.println("Fnety is " + Fnety);

		double F;
		F = Samh.calcForceExertedByX(Rocinante);
		System.out.println("F is " + F);
	}

}
