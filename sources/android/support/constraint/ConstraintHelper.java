package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.C0016R.C0017id;
import android.support.constraint.solver.widgets.Helper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
    protected int mCount = 0;
    protected Helper mHelperWidget = null;
    protected int[] mIds = new int[32];
    private String mReferenceIds;
    protected boolean mUseViewMeasure = false;
    protected Context myContext;

    public ConstraintHelper(Context context) {
        super(context);
        this.myContext = context;
        init(null);
    }

    public ConstraintHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.myContext = context;
        init(attrs);
    }

    public ConstraintHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.myContext = context;
        init(attrs);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0016R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == C0016R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    this.mReferenceIds = a.getString(attr);
                    setIds(this.mReferenceIds);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void setReferencedIds(int[] ids) {
        this.mCount = 0;
        for (int tag : ids) {
            setTag(tag, null);
        }
    }

    public void setTag(int tag, Object value) {
        if (this.mCount + 1 > this.mIds.length) {
            this.mIds = Arrays.copyOf(this.mIds, this.mIds.length * 2);
        }
        this.mIds[this.mCount] = tag;
        this.mCount++;
    }

    public void onDraw(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mUseViewMeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void validateParams() {
        if (this.mHelperWidget != null) {
            LayoutParams params = getLayoutParams();
            if (params instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) params).widget = this.mHelperWidget;
            }
        }
    }

    private void addID(String idString) {
        if (idString != null && this.myContext != null) {
            String idString2 = idString.trim();
            int tag = 0;
            try {
                tag = C0017id.class.getField(idString2).getInt(null);
            } catch (Exception e) {
            }
            if (tag == 0) {
                tag = this.myContext.getResources().getIdentifier(idString2, "id", this.myContext.getPackageName());
            }
            if (tag == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout)) {
                Object value = ((ConstraintLayout) getParent()).getDesignInformation(0, idString2);
                if (value != null && (value instanceof Integer)) {
                    tag = ((Integer) value).intValue();
                }
            }
            if (tag != 0) {
                setTag(tag, null);
            } else {
                Log.w("ConstraintHelper", "Could not find id of \"" + idString2 + "\"");
            }
        }
    }

    private void setIds(String idList) {
        if (idList != null) {
            int begin = 0;
            while (true) {
                int end = idList.indexOf(44, begin);
                if (end == -1) {
                    addID(idList.substring(begin));
                    return;
                } else {
                    addID(idList.substring(begin, end));
                    begin = end + 1;
                }
            }
        }
    }

    public void updatePreLayout(ConstraintLayout container) {
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        if (this.mHelperWidget != null) {
            this.mHelperWidget.removeAllIds();
            for (int i = 0; i < this.mCount; i++) {
                View view = container.findViewById(this.mIds[i]);
                if (view != null) {
                    this.mHelperWidget.add(container.getViewWidget(view));
                }
            }
        }
    }

    public void updatePostLayout(ConstraintLayout container) {
    }

    public void updatePostMeasure(ConstraintLayout container) {
    }
}
