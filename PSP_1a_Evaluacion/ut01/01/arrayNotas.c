#include <stdio.h>
#include <string.h>
#define nNotas 5
#define mAprobado 5
#define Aprobado "aprobado"
#define Suspendido "suspendido"


int main()
{
    float notas[nNotas];
    float nota;
    
    for (int i = 0; i < nNotas; i++)
    {
        printf("Introduce nota del examen %d: ", i+1);
        scanf("%f", &notas[i]);        
    }

    float nMayor = notas[0];
    float nMenor = notas[0];
    float nTotal = 0;
for (int i = 0; i < nNotas; i++) 
  {    
        if (nMayor < notas[i]) 
        {            
            nMayor = notas[i];
        }
        else
        {
            nMenor = notas[i];
        }      
        
        printf("Nota del examen %d: %.2f %s\n", i+1, notas[i], notas[i] >=mAprobado ? Aprobado : Suspendido);
        nTotal += notas[i];
    } 
            
  
    float nMedia = nTotal/nNotas;

    printf("La nota mayor es: %.2f \nY la menor es: %.2f\n", nMayor, nMenor);
    printf("La nota media es: %.2f\n",nMedia);
    printf("Has %s la asignatura\n", nMedia >= mAprobado ? "aprobado" : "suspendido");

    return 0;
}