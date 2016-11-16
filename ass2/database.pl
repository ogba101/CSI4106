/* human attributes */
color(green). color(blue). 

height(tall). height(short). 

eyes(blue). eyes(brown).

/* men */
man(chris).
man(john).
man(dave).
man(paul).
man(elias).

/* women */
woman(lisa). 
woman(stella). 
woman(jessica). 
woman(bell).

eye_color(john,blue).
eye_color(chris, blue).
eye_color(dave,brown).
eye_color(paul,brown).
eye_color(elias,brown).

eye_color(lisa,brown).
eye_color(stella,brown).
eye_color(jessica,brown).
eye_color(bell,brown).

height(john,short).
height(chris, tall).
height(dave,tall).
height(paul,short).
height(elias,tall).

height(lisa,tall).
height(stella,tall).
height(jessica,tall).
height(bell,tall).

skin_color(chris,green).
skin_color(john,blue).
skin_color(dave,green).
skin_color(paul,green).
skin_color(elias,green).

skin_color(lisa,green).
skin_color(stella,green).
skin_color(jessica,green).
skin_color(bell,green).

/*  what defines a person */
person(X) :- man(X).
person(X) :- woman(X).

/* who is doing what */
activity(chris, dinner, 7).
activity(chris, movie, 9).
activity(chris, dancing, 11).

activity(john, soccer, 7).

activity(paul, soccer, 7).
activity(paul, coffee, 9).
activity(paul, dancing, 11).

/* weapons */
weapon(gun).
weapon(knife).
weapon(rope).
weapon(poison).

trained(chris, gun).
trained(chris, poison).

trained(paul, gun).
trained(paul, rope).

trained(paul, poison).

trained(lisa, gun).
trained(lisa, rope).
trained(lisa, poison).

trained(stella, rope).

trained(jessica, poison).

trained(bell, gun).

trained(X, knife) :- person(X). /* everyone knows how to use a knife */

/* relationships */
parent(chris, john).
parent(lisa, john).

parent(chris, paul).
parent(lisa, paul).

parent(john, dave).
parent(stella, dave).

sibling(X,Y) :- parent(Z,X),parent(Z,Y).

/* who loves who */
loves(chris,lisa).
loves(lisa,chris).
loves(john,stella).
loves(stella,dave).
loves(dave,stella).
loves(lisa,stella).

/* Who is married */
gotmarried(chris, lisa).
gotmarried(john, stella).
gotmarried(dave, bell).
married(X,Y) :- gotmarried(X,Y).
married(X,Y) :- gotmarried(Y,X).

/* troubled marriages */
jealous(X,Y) :- married(X, Z), loves(Z,Y), X \== Y.
married_not_in_love(X) :- married(X,Y), \+ loves(X,Y). 

/* murder solving rules */
investigate_murder(X,T,W,Y) :- jealous(Y, X),  trained(Y, W), \+ activity(Y, _Z, T).
investigate_murder(X,T,W,Y) :- married_not_in_love(Y), married(X,Y), trained(Y, W),  \+ activity(Y, _Z, T).

