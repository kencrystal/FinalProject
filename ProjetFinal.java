public class ProjetFinal {

 Ball ball; // Define the ball as a global object 

Paddle paddleLeft;
Paddle paddleRight;

int scoreLeft = 0; // Les points au début du jeu. On commence avec 0 
int scoreRight = 0;

void setup(){
  size(800,600);
  ball = new Ball(width/2, height/2, 50); // La taille et la grosseur du ballon dans le jeu.
  ball.speedX = 4; // Ceci est la vitesse du ballon sur l'axe des x
  ball.speedY = random(4,8); // La vitesse du ballon sur l'axe des y
  
  paddleLeft = new Paddle(15, height/2, 30,200); // la taille du plaque du côté gauche  
  paddleRight = new Paddle(width-15, height/2, 30,200); // La taille du plaque du côté droite 

} // Ferme le setup 

void draw(){
  background(0); // une toile transparente
  ball.move(); //c sa calculue une nouvelle endroit pour la balle
  ball.display(); // Sa place le ballon dans le jeu
  
  // le mouvement des plaques
  paddleLeft.move();
  paddleLeft.display();
  paddleRight.move();
  paddleRight.display();

  // Ces if assigne les points selon la direction du ballon. De là, les points s'affiche sur l'écran des joeurs au millieu de l'écran. (Score)
  if (ball.right() > width) { 
    scoreLeft = scoreLeft + 1;
    ball.x = width/2; //Ceci est le ballon se place lorsque le jeu finit sur le côté right
    ball.y = height/2;
  }
  if (ball.left() < 0) {
    scoreRight = scoreRight + 1;
    ball.x = width/2; //  Ceci est le ballon se place lorsque le jeu finit sur le côté gauche 
    ball.y = height/2;
  }

  if (ball.bottom() > height) { // Ceci est la vitesse du ballon lorsque le jeu recommence.
    ball.speedY = -ball.speedY;
  }

  if (ball.top() < 0) { // Ceci est la vitesse du ballon lorsque le jeu recommence.
    ball.speedY = -ball.speedY;
  }
  
  if (paddleLeft.bottom() > height) { // La hauteur de la plaque gauche est plus grande que la hauteur de l'écran sa le rétracir 
    paddleLeft.y = height-paddleLeft.h/2;
  }

  if (paddleLeft.top() < 0) { // Si la plaque est plus petit que 0 la hauteur de la plaque grandit
    paddleLeft.y = paddleLeft.h/2;
  }
    
  if (paddleRight.bottom() > height) {
    paddleRight.y = height-paddleRight.h/2;
  }

  if (paddleRight.top() < 0) {
    paddleRight.y = paddleRight.h/2;
  }
  
  
  // Si la balle vas derriere la plaque 
  // SI le ballon est dans l'endroit de la plaque entre le haut et bas de la plaque 
  // Rebondi le ballon a l'autre côté

  if ( ball.left() < paddleLeft.right() && ball.y > paddleLeft.top() && ball.y < paddleLeft.bottom()){
    ball.speedX = -ball.speedX;
    ball.speedY = map(ball.y - paddleLeft.y, -paddleLeft.h/2, paddleLeft.h/2, -10, 10);
  }

  if ( ball.right() > paddleRight.left() && ball.y > paddleRight.top() && ball.y < paddleRight.bottom()) {
    ball.speedX = -ball.speedX;
    ball.speedY = map(ball.y - paddleRight.y, -paddleRight.h/2, paddleRight.h/2, -10, 10);
  }

 
  textSize(40);
  textAlign(CENTER);
  text(scoreRight, width/2+50, 30); // Le score du côté droit
  text(scoreLeft, width/2-50, 30); // Le score du côté gauche
}
// Ces if détermine la vitesse des plaques 
void keyPressed(){
  if(keyCode == UP){
    paddleRight.speedY=-6;
  }
  if(keyCode == DOWN){
    paddleRight.speedY=6;
  }
  if(key == 'w'){
    paddleLeft.speedY=-6;
  }
  if(key == 's'){
    paddleLeft.speedY=6;
  }
}

void keyReleased(){
  if(keyCode == UP){
    paddleRight.speedY=-6;
  }
  if(keyCode == DOWN){
    paddleRight.speedY=6;
  }
  if(key == 'a'){
    paddleLeft.speedY=-6;
  }
  if(key == 'z'){
    paddleLeft.speedY=6;
  }
}


class Ball {
  float x;
  float y;
  float speedX;
  float speedY;
  float diameter;
  color c;
  
  // Constructor method
  Ball(float tempX, float tempY, float tempDiameter) {
    x = tempX;
    y = tempY;
    diameter = tempDiameter;
    speedX = 0;
    speedY = 0;
    c = (225); 
  }
  
  void move() {
    // Ajoute de la vitesse à l'endroit 
    y = y + speedY;
    x = x + speedX;
  }
  
  void display() {
    fill(c); //set the drawing color
    ellipse(x,y,diameter,diameter); //dessiner un cercle 
  }
  
  // Des fonctions d'aide à la détection des collisions
  float left(){
    return x-diameter/2;
  }
  float right(){
    return x+diameter/2;
  }
  float top(){
    return y-diameter/2;
  }
  float bottom(){
    return y+diameter/2;
  }

}




class Paddle{

  float x;
  float y;
  float w;
  float h;
  float speedY;
  float speedX;
  color c;
  
  Paddle(float tempX, float tempY, float tempW, float tempH){
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    speedY = 0;
    speedX = 0;
    c=(255);
  }

  void move(){
    y += speedY;
    x += speedX;
  }

  void display(){
    fill(c);
    rect(x-w/2,y-h/2,w,h);
  } 

  // Fonction qui affiche le gagnant du match. (Ne marche pas)
  if (scoreLeft == 4 ){
    System.out.print("le joeur gauche à gagner. Félicitation");
  }
  if (scoreRight == 4 ){
    System.out.print("Le joueur droit à gagner. Félicitation");
  }

  float left(){
    return x-w/2;
  }
  float right(){
    return x+w/2;
  }
  float top(){
    return y-h/2;
  }
  float bottom(){
    return y+h/2;
  }
}


    
}
