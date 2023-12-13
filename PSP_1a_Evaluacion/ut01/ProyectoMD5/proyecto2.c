#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <time.h>
#define MD5_LEN 16
// Hashes proporcionados
char *hashes[] = {
    "f4a1c8901a3d406f17af67144a3ec71a",
    "d66e29062829e8ae0313adc5a673f863"};

// Caracteres ascii minúsculas
char charset[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

// Longitud de la contraseña original
int password_length = 5;

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

void brute_force()
{
    char candidate[6];
    char hash[MD5_LEN];
    clock_t start, end;
    double cpu_time_used;

    start = clock();
    
    for (int c1 = 0; c1 < strlen(charset); c1++)
    {
        candidate[0] = charset[c1];
        for (int c2 = 0; c2 < strlen(charset); c2++)
        {
            candidate[1] = charset[c2];
            for (int c3 = 0; c3 < strlen(charset); c3++)
            {
                candidate[2] = charset[c3];
                for (int c4 = 0; c4 < strlen(charset); c4++)
                {
                    candidate[3] = charset[c4];
                    
                    for (int c5 = 0; c5 < strlen(charset); c5++)
                    {
                        candidate[4] = charset[c5];
                        candidate[5] = '\0';
                        generateMD5(candidate, hash);
                        for (int i = 0; i < sizeof(hashes) / sizeof(hashes[0]); i++)
                        {
                            if (strcmp(hashes[i], hash) == 0)
                            {
                                printf("Contraseña encontrada: %s (Hash: %s)\n", candidate, hashes[i]);
                            }
                        }
                    }
                }
            }
        }
    }

        end = clock(); // Guardamos el tiempo de finalización

        // Calculamos el tiempo de ejecución en segundos
        // Tomamos la diferencia entre el tiempo de finalización y el tiempo de inicio
        // Luego dividimos por CLOCKS_PER_SEC para obtener el tiempo en segundos
        cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
        printf("Tiempo de ejecución: %f segundos\n", cpu_time_used);
    }

    int main()
    {
        brute_force();
        return 0;
    }
