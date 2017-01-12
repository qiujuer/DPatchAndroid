package net.qiujuer.dpatch;

public interface DPatch {
    int diff(String oldPath, String newPath, String patchPath);

    int patch(String oldPath, String newPath, String patchPath);
}
