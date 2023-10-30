#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <time.h>
#define MD5_LEN 16

void generateMD5(const char *string, unsigned char *str_result)
{
    EVP_MD_CTX *ctx;
    const EVP_MD *md;
    unsigned char result[EVP_MAX_MD_SIZE];

    ctx = EVP_MD_CTX_new();
    md = EVP_md5();

    EVP_DigestInit_ex(ctx, md, NULL);
    EVP_DigestUpdate(ctx, string, strlen(string));
    EVP_DigestFinal_ex(ctx, result, NULL);

    EVP_MD_CTX_free(ctx);

    for (int i = 0; i < MD5_LEN; i++)
    { // MD5 result is always 16 bytes
        sprintf(str_result + (i * 2), "%02x", result[i]);
    }
}

int realizarTrabajoDeHijo(int soy_hijo, int n_procesos)
{
    char *hashes[] = {
        "582fc884d6299814fbd4f12c1f9ae70f",
        "74437fabd7c8e8fd178ae89acbe446f2",
        "28ea19352381b8659df830dd6d5c90a3",
        "90f077d7759d0d4d21e6867727d4b2bd",
    };
    char alfabeto[] = "abcdefghijklmnopqrstuvwxyz";
    char cadena[5];
    char hash[EVP_MAX_MD_SIZE];

    for (int j = 0; j < strlen(alfabeto); j++)
    {
        if (j % n_procesos == soy_hijo)
        {
            cadena[0] = alfabeto[j];
            for (int c2 = 0; c2 < strlen(alfabeto); c2++)
            {
                cadena[1] = alfabeto[c2];
                for (int c3 = 0; c3 < strlen(alfabeto); c3++)
                {
                    cadena[2] = alfabeto[c3];
                    for (int c4 = 0; c4 < strlen(alfabeto); c4++)
                    {
                        cadena[3] = alfabeto[c4];
                        cadena[4] = '\0';
                        generateMD5(cadena, hash);
                        for (int i = 0; i < 4; i++)
                        {
                            if (strcmp(hash, hashes[i]) == 0)
                            {
                                printf("%s\n", cadena);
                            }
                        }
                    }
                }
            }
        }
    }

    return 0;
}
int main()
{

    int n_procesos;

    int soy_hijo = 0;

    printf("Introduce el numero de procesos: \n");
    scanf("%d", &n_procesos);
    pid_t id_hijos[n_procesos];

    for (soy_hijo = 0; soy_hijo < n_procesos; soy_hijo++)
    {
        id_hijos[soy_hijo] = fork();
        if (id_hijos[soy_hijo] == 0)
        {
            break;
        }
    }
    if (soy_hijo == n_procesos)
    {
        // Soy el padre
        for (int i = 0; i < n_procesos; i++)
        {
            wait(NULL);
        }
    }
    else
    {
        realizarTrabajoDeHijo(soy_hijo, n_procesos);
    }

    return 0;
}
