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
int main()
{
    char *hashes[] = {
        "582fc884d6299814fbd4f12c1f9ae70f",
        "74437fabd7c8e8fd178ae89acbe446f2",
        "28ea19352381b8659df830dd6d5c90a3",
        "90f077d7759d0d4d21e6867727d4b2bd",
    };
    char letras[] = "abcdefghijklmnopqrstuvwxyz";
    char cadena[5];
    char hash[EVP_MAX_MD_SIZE];
    pid_t id_hijo;

    if (id_hijo != 0)
    {
        for (int c1 = 0; c1 < letras[13]; c1++)
        {
            cadena[0] = letras[c1];
            for (int c2 = 0; c2 < strlen(letras); c2++)
            {
                cadena[1] = letras[c2];
                for (int c3 = 0; c3 < strlen(letras); c3++)
                {
                    cadena[2] = letras[c3];
                    for (int c4 = 0; c4 < strlen(letras); c4++)
                    {
                        cadena[3] = letras[c4];
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
    }else{
        for (int c1 = 12; c1 < strlen(letras); c1++)
        {
            cadena[0] = letras[c1];
            for (int c2 = 0; c2 < strlen(letras); c2++)
            {
                cadena[1] = letras[c2];
                for (int c3 = 0; c3 < strlen(letras); c3++)
                {
                    cadena[2] = letras[c3];
                    for (int c4 = 0; c4 < strlen(letras); c4++)
                    {
                        cadena[3] = letras[c4];
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