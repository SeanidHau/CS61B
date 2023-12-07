package CS61B.CS61B.proj0;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;


    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) +
                (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return G * mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double distanceX = p.xxPos - xxPos;
        return calcForceExertedBy(p) * distanceX / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double distanceY = p.yyPos - yyPos;
        return calcForceExertedBy(p) * distanceY / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allplanets) {
        double totalForce = 0;
        for(Planet p : allplanets) {
            if(this.equals(p)) {
                continue;
            }
            totalForce += calcForceExertedByX(p);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] allplanets) {
        double totalForce = 0;
        for(Planet p : allplanets) {
            if(this.equals(p)) {
                continue;
            }
            totalForce += calcForceExertedByY(p);
        }
        return totalForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

}
