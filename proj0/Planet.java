public class Planet{
    
    /** 6 instantanious variables*/
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    // the first constructor
    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    //the second constructor
    public Planet (Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    
    public double calcDistance(Planet p) {
        double result;
        double disx = p.xxPos - this.xxPos;
        double disy = p.yyPos - this.yyPos;
        result = Math.sqrt(disx * disx + disy * disy) ;
        return result;
    }

    public double calcForceExertedBy(Planet p) {
        double result;
        double dis = calcDistance(p);
        result = (G * this.mass * p.mass) / (dis * dis);
        return result;
    }

    public double calcForceExertedByX(Planet p) {
        double result;
        double force = calcForceExertedBy(p);
        double dis = calcDistance(p);
        result = force * (p.xxPos - this.xxPos) / dis;
        return result;
    }

    public double calcForceExertedByY(Planet p) {
        double result;
        double force = calcForceExertedBy(p);
        double dis = calcDistance(p);
        result = force * (p.yyPos - this.yyPos) / dis;
        return result;
    }

    public double calcNetForceExertedByX(Planet[] all) {
        double result = 0;
        for (Planet p : all) {
            if (this.equals(p)) {
                continue;
            }
            double force = calcForceExertedBy(p);
            double dis = calcDistance(p);
            result = result + (force * (p.xxPos - this.xxPos) / dis);
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] all) {
        double result = 0;
        for (Planet p : all) {
            if (this.equals(p)) {
                continue;
            }
            double force = calcForceExertedBy(p);
            double dis = calcDistance(p);
            result = result + (force * (p.yyPos - this.yyPos) / dis);
        }
        return result;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx/this.mass;
        double ay = fy/this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw (){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}
