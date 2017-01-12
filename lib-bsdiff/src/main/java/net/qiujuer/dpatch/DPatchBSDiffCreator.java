package net.qiujuer.dpatch;

import net.qiujuer.dpatch.lib.BSDiff;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public final class DPatchBSDiffCreator implements DPatch {
    private DPatchBSDiffCreator() {

    }

    public static DPatch create() {
        return new DPatchBSDiffCreator();
    }

    @Override
    public int diff(String oldPath, String newPath, String patchPath) {
        return BSDiff.diff(oldPath,newPath,patchPath);
    }

    @Override
    public int patch(String oldPath, String newPath, String patchPath) {
        return BSDiff.patch(oldPath,newPath,patchPath);
    }
}
