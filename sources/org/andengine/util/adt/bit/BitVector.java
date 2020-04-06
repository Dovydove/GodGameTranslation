package org.andengine.util.adt.bit;

public final class BitVector {
    private final int mCapacity;
    private final long[] mData;

    private BitVector(int pCapacity) {
        int dataCapacity;
        if (pCapacity <= 0) {
            throw new IllegalArgumentException("pCapacity must be > 0.");
        }
        this.mCapacity = pCapacity;
        if (pCapacity % 64 == 0) {
            dataCapacity = pCapacity / 64;
        } else {
            dataCapacity = (pCapacity / 64) + 1;
        }
        this.mData = new long[dataCapacity];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ca, code lost:
        r3[r9] = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00db, code lost:
        r10 = r10 | ((((long) r21[r8 + 5]) << 16) & 16711680);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00e9, code lost:
        r10 = r10 | ((((long) r21[r8 + 4]) << 24) & 4278190080L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00f9, code lost:
        r10 = r10 | ((((long) r21[r8 + 3]) << 32) & 1095216660480L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0109, code lost:
        r10 = r10 | ((((long) r21[r8 + 2]) << 40) & 280375465082880L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0119, code lost:
        r10 = r10 | ((((long) r21[r8 + 1]) << 48) & 71776119061217280L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0126, code lost:
        r10 = r10 | ((((long) r21[r8 + 0]) << 56) & -72057594037927936L);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BitVector(byte[] r21) {
        /*
            r20 = this;
            r0 = r21
            int r13 = r0.length
            int r13 = r13 * 8
            r0 = r20
            r0.<init>(r13)
            r0 = r20
            long[] r3 = r0.mData
            r0 = r21
            int r13 = r0.length
            int r13 = r13 % 8
            if (r13 != 0) goto L_0x00b3
            r12 = 1
        L_0x0016:
            int r4 = r3.length
            if (r12 == 0) goto L_0x00b6
            int r6 = r4 + -1
        L_0x001b:
            r5 = r6
        L_0x001c:
            if (r5 < 0) goto L_0x00ba
            int r2 = r5 * 8
            int r13 = r2 + 0
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 56
            long r14 = r14 << r13
            r16 = -72057594037927936(0xff00000000000000, double:-5.4861240687936887E303)
            long r14 = r14 & r16
            int r13 = r2 + 1
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 48
            long r16 = r16 << r13
            r18 = 71776119061217280(0xff000000000000, double:7.06327445644526E-304)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 2
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 40
            long r16 = r16 << r13
            r18 = 280375465082880(0xff0000000000, double:1.38523885234213E-309)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 3
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 32
            long r16 = r16 << r13
            r18 = 1095216660480(0xff00000000, double:5.41108926696E-312)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 4
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 24
            long r16 = r16 << r13
            r18 = 4278190080(0xff000000, double:2.113706745E-314)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 5
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 16
            long r16 = r16 << r13
            r18 = 16711680(0xff0000, double:8.256667E-317)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 6
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 8
            long r16 = r16 << r13
            r18 = 65280(0xff00, double:3.22526E-319)
            long r16 = r16 & r18
            long r14 = r14 | r16
            int r13 = r2 + 7
            byte r13 = r21[r13]
            long r0 = (long) r13
            r16 = r0
            r13 = 0
            long r16 = r16 << r13
            r18 = 255(0xff, double:1.26E-321)
            long r16 = r16 & r18
            long r14 = r14 | r16
            r3[r5] = r14
            int r5 = r5 + -1
            goto L_0x001c
        L_0x00b3:
            r12 = 0
            goto L_0x0016
        L_0x00b6:
            int r6 = r4 + -2
            goto L_0x001b
        L_0x00ba:
            if (r12 != 0) goto L_0x00cc
            r10 = 0
            int r9 = r4 + -1
            int r8 = r9 * 8
            r0 = r21
            int r13 = r0.length
            int r7 = r13 - r8
            switch(r7) {
                case 1: goto L_0x0126;
                case 2: goto L_0x0119;
                case 3: goto L_0x0109;
                case 4: goto L_0x00f9;
                case 5: goto L_0x00e9;
                case 6: goto L_0x00db;
                case 7: goto L_0x00cd;
                default: goto L_0x00ca;
            }
        L_0x00ca:
            r3[r9] = r10
        L_0x00cc:
            return
        L_0x00cd:
            int r13 = r8 + 6
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 8
            long r14 = r14 << r13
            r16 = 65280(0xff00, double:3.22526E-319)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x00db:
            int r13 = r8 + 5
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 16
            long r14 = r14 << r13
            r16 = 16711680(0xff0000, double:8.256667E-317)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x00e9:
            int r13 = r8 + 4
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 24
            long r14 = r14 << r13
            r16 = 4278190080(0xff000000, double:2.113706745E-314)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x00f9:
            int r13 = r8 + 3
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 32
            long r14 = r14 << r13
            r16 = 1095216660480(0xff00000000, double:5.41108926696E-312)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x0109:
            int r13 = r8 + 2
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 40
            long r14 = r14 << r13
            r16 = 280375465082880(0xff0000000000, double:1.38523885234213E-309)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x0119:
            int r13 = r8 + 1
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 48
            long r14 = r14 << r13
            r16 = 71776119061217280(0xff000000000000, double:7.06327445644526E-304)
            long r14 = r14 & r16
            long r10 = r10 | r14
        L_0x0126:
            int r13 = r8 + 0
            byte r13 = r21[r13]
            long r14 = (long) r13
            r13 = 56
            long r14 = r14 << r13
            r16 = -72057594037927936(0xff00000000000000, double:-5.4861240687936887E303)
            long r14 = r14 & r16
            long r10 = r10 | r14
            goto L_0x00ca
        */
        throw new UnsupportedOperationException("Method not decompiled: org.andengine.util.adt.bit.BitVector.<init>(byte[]):void");
    }

    public int getCapacity() {
        return this.mCapacity;
    }

    public boolean getBit(int pPosition) {
        if (pPosition < 0) {
            throw new IllegalArgumentException("pPosition must be >= 0.");
        } else if (pPosition >= this.mCapacity) {
            throw new IllegalArgumentException("pPosition must be < capacity.");
        } else {
            return ((this.mData[pPosition / 64] >> ((64 - (pPosition % 64)) + -1)) & 1) != 0;
        }
    }

    public byte getByte(int pPosition) {
        return (byte) ((int) getBits(pPosition, 8));
    }

    public short getShort(int pPosition) {
        return (short) ((int) getBits(pPosition, 16));
    }

    public int getInt(int pPosition) {
        return (int) getBits(pPosition, 32);
    }

    public long getLong(int pPosition) {
        return getBits(pPosition, 64);
    }

    public long getBits(int pPosition, int pLength) {
        long data;
        if (pPosition < 0) {
            throw new IllegalArgumentException("pPosition must be >= 0.");
        } else if (pLength < 0) {
            throw new IllegalArgumentException("pLength must be >= 0.");
        } else if (pPosition + pLength > this.mCapacity) {
            throw new IllegalArgumentException("pPosition + pLength must be <= capacity.");
        } else if (pLength == 0) {
            return 0;
        } else {
            int dataIndex = pPosition / 64;
            int offset = pPosition % 64;
            if (offset == 0) {
                data = this.mData[dataIndex];
            } else if (offset + pLength <= 64) {
                data = this.mData[dataIndex] << offset;
            } else {
                data = (this.mData[dataIndex] << offset) | (this.mData[dataIndex + 1] >>> (64 - offset));
            }
            if (pLength == 64) {
                return data;
            }
            int rightShift = 64 - pLength;
            return (data >> rightShift) & (-1 >>> rightShift);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < this.mCapacity; i++) {
            sb.append(getBit(i) ? '1' : '0');
            if (i % 8 == 7 && i < this.mCapacity - 1) {
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0122, code lost:
        r3[r13 + 5] = (byte) ((int) ((r14 >> 16) & 255));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0138, code lost:
        r3[r13 + 4] = (byte) ((int) ((r14 >> 24) & 255));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x014e, code lost:
        r3[r13 + 3] = (byte) ((int) ((r14 >> 32) & 255));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0164, code lost:
        r3[r13 + 2] = (byte) ((int) ((r14 >> 40) & 255));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x017a, code lost:
        r3[r13 + 1] = (byte) ((int) ((r14 >> 48) & 255));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0190, code lost:
        r3[r13 + 0] = (byte) ((int) ((r14 >> 56) & 255));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] toByteArray() {
        /*
            r24 = this;
            r0 = r24
            int r0 = r0.mCapacity
            r18 = r0
            int r18 = r18 % 8
            if (r18 != 0) goto L_0x00e7
            r0 = r24
            int r0 = r0.mCapacity
            r18 = r0
            int r2 = r18 / 8
        L_0x0012:
            byte[] r3 = new byte[r2]
            r0 = r24
            int r0 = r0.mCapacity
            r18 = r0
            int r18 = r18 % 64
            if (r18 != 0) goto L_0x00f3
            r17 = 1
        L_0x0020:
            r0 = r24
            long[] r6 = r0.mData
            int r7 = r6.length
            if (r17 == 0) goto L_0x00f7
            int r11 = r7 + -1
        L_0x0029:
            int r18 = r11 * 8
            int r4 = r18 + 7
            r10 = r11
            r5 = r4
        L_0x002f:
            if (r10 < 0) goto L_0x00fb
            r8 = r6[r10]
            int r4 = r5 + -1
            r18 = 0
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r5] = r18
            int r5 = r4 + -1
            r18 = 8
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r4] = r18
            int r4 = r5 + -1
            r18 = 16
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r5] = r18
            int r5 = r4 + -1
            r18 = 24
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r4] = r18
            int r4 = r5 + -1
            r18 = 32
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r5] = r18
            int r5 = r4 + -1
            r18 = 40
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r4] = r18
            int r4 = r5 + -1
            r18 = 48
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r5] = r18
            int r5 = r4 + -1
            r18 = 56
            long r18 = r8 >> r18
            r20 = 255(0xff, double:1.26E-321)
            long r18 = r18 & r20
            r0 = r18
            int r0 = (int) r0
            r18 = r0
            r0 = r18
            byte r0 = (byte) r0
            r18 = r0
            r3[r4] = r18
            int r10 = r10 + -1
            goto L_0x002f
        L_0x00e7:
            r0 = r24
            int r0 = r0.mCapacity
            r18 = r0
            int r18 = r18 / 8
            int r2 = r18 + 1
            goto L_0x0012
        L_0x00f3:
            r17 = 0
            goto L_0x0020
        L_0x00f7:
            int r11 = r7 + -2
            goto L_0x0029
        L_0x00fb:
            if (r17 != 0) goto L_0x010b
            int r16 = r7 + -1
            r14 = r6[r16]
            int r13 = r16 * 8
            int r0 = r3.length
            r18 = r0
            int r12 = r18 % 8
            switch(r12) {
                case 1: goto L_0x0190;
                case 2: goto L_0x017a;
                case 3: goto L_0x0164;
                case 4: goto L_0x014e;
                case 5: goto L_0x0138;
                case 6: goto L_0x0122;
                case 7: goto L_0x010c;
                default: goto L_0x010b;
            }
        L_0x010b:
            return r3
        L_0x010c:
            int r18 = r13 + 6
            r19 = 8
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x0122:
            int r18 = r13 + 5
            r19 = 16
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x0138:
            int r18 = r13 + 4
            r19 = 24
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x014e:
            int r18 = r13 + 3
            r19 = 32
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x0164:
            int r18 = r13 + 2
            r19 = 40
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x017a:
            int r18 = r13 + 1
            r19 = 48
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
        L_0x0190:
            int r18 = r13 + 0
            r19 = 56
            long r20 = r14 >> r19
            r22 = 255(0xff, double:1.26E-321)
            long r20 = r20 & r22
            r0 = r20
            int r0 = (int) r0
            r19 = r0
            r0 = r19
            byte r0 = (byte) r0
            r19 = r0
            r3[r18] = r19
            goto L_0x010b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.andengine.util.adt.bit.BitVector.toByteArray():byte[]");
    }
}
