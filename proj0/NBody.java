import sun.awt.www.content.image.png;

public class NBody{
    //no constructor

    //read given file radius (2nd line)
    public static double readRadius(String name) {
        
        In input = new In(name);
        input.readInt();
        double secondItemInFile = input.readDouble();
        return secondItemInFile;
    }

    //read planet
    public static Planet[] readPlanets(String name) {
        In input = new In(name);
        int N = input.readInt();
        input.readDouble();
        Planet[] result = new Planet[N];

        for (int i = 0; i < N; i = i + 1) {
            double xP = input.readDouble();
            double yP = input.readDouble();
            double xV = input.readDouble();
            double yV = input.readDouble();
            double m = input.readDouble();
            String img = input.readString();
            result[i] = new Planet(xP, yP, xV, yV, m, img);
        }

        return result;
    }

    public static void main (String[] args) {
        
        //collect all needed input;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] p = readPlanets(filename);
        String BG = "images/starfield.jpg";

        //draw the BG
        In input = new In(filename);
        int N = input.readInt();
        double r = input.readDouble();
        StdDraw.setScale(-r, r);
        StdDraw.picture(0, 0, BG);

        // draw the planet
        for (int i = 0; i < N; i = i + 1) {
            p[i].draw();
        }

        //animation
        StdDraw.enableDoubleBuffering();

        for (double i = 0; i <= T; i = i + dt) {
            double[] xForces = new double [N];
            double[] yForces = new double [N];
            for (int j = 0; j < N; j = j + 1) {
                xForces[j] = p[j].calcNetForceExertedByX(p); 
                yForces[j] = p[j].calcNetForceExertedByY(p);

            }
            for (int k = 0; k < N; k=k+1) {
                p[k].update(dt, xForces[k], yForces[k]);
            }

             //draw the BG
            StdDraw.setScale(-r, r);
            StdDraw.picture(0, 0, BG);

            // draw the planet
            for (int l = 0; l < N; l = l + 1) {
                p[l].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", r);

        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  p[i].xxPos, p[i].yyPos, p[i].xxVel,
                  p[i].yyVel, p[i].mass, p[i].imgFileName);   
        }

    }

}
