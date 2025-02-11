import React, { ReactNode } from "react";

// *** STYLES ***
import styles from "./MotionWrapper.module.scss";

const MotionWrapper = ({
  active,
  children,
}: {
  active?: boolean;
  children: ReactNode;
}) => {
  return <div className={styles.motionWrapper}>{children}</div>;
};

export default MotionWrapper;
