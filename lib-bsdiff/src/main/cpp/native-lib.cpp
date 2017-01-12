#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

#define JNI_METHOD(METHOD_NAME) Java_net_qiujuer_dpatch_lib_BSDiff_##METHOD_NAME

extern int diff(int argc, char *argv[]);
extern int patch(int argc, char *argv[]);

JNIEXPORT jint JNICALL JNI_METHOD(diff)(JNIEnv *env, jobject object,
                                        jstring old_path, jstring new_path, jstring patch_path) {
    int argc = 4;
    char *argv[argc];
    argv[0] = (char *) "bsdiff";
    argv[1] = (char *) (env)->GetStringUTFChars(old_path, 0);
    argv[2] = (char *) (env)->GetStringUTFChars(new_path, 0);
    argv[3] = (char *) (env)->GetStringUTFChars(patch_path, 0);
    bool isCrash = false;
    int ret;
    try {
        ret = diff(argc, argv);
    }
    catch (...) {
        isCrash = true;
    }
    (env)->ReleaseStringUTFChars(old_path, argv[1]);
    (env)->ReleaseStringUTFChars(new_path, argv[2]);
    (env)->ReleaseStringUTFChars(patch_path, argv[3]);
    return isCrash ? -1 : ret;
}

JNIEXPORT jint JNICALL JNI_METHOD(patch)(JNIEnv *env, jobject object,
                                         jstring old_path, jstring new_path, jstring patch_path) {
    int argc = 4;
    char *argv[argc];
    argv[0] = (char *) "bspatch";
    argv[1] = (char *) (env)->GetStringUTFChars(old_path, 0);
    argv[2] = (char *) (env)->GetStringUTFChars(new_path, 0);
    argv[3] = (char *) (env)->GetStringUTFChars(patch_path, 0);
    bool isCrash = false;
    int ret;
    try {
        ret = patch(argc, argv);
    }
    catch (...) {
        isCrash = true;
    }
    (env)->ReleaseStringUTFChars(old_path, argv[1]);
    (env)->ReleaseStringUTFChars(new_path, argv[2]);
    (env)->ReleaseStringUTFChars(patch_path, argv[3]);
    return isCrash ? -1 : ret;
}
#ifdef __cplusplus
}
#endif