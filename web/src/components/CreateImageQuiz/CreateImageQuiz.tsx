"use client";
import React from "react";
import createImageQuizContainer from "@/components/CreateImageQuiz/CreateImageQuiz.container";

import styles from "./CreateImageQuiz.module.scss";

const CreateImageQuiz = () => {
  const {
    states: { position, position2, elemParams },
    functions: {
      setPosition,
      onDrag,
      onDrag2,
      onElemDrag,
      onDragSizeStart,
      onDragSizeEnd,
    },
  } = createImageQuizContainer();
  return (
    <div
      style={{
        position: "relative",
        // width: "50px",
        // height: "100%",
        // margin: "auto",
        // display: "flex",
        // backgroundColor: "red",
      }}
    >
      <div
        style={{
          width: elemParams.width,
          height: elemParams.height,

          position: "absolute",
          left: position.x,
          top: position.y,
          cursor: "pointer",
        }}
        className={styles.createImageQuiz_highlightBox}
        draggable
        onDrag={onElemDrag}
        onDragStart={(event) => {
          let img = new Image();
          img.src =
            "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
          event.dataTransfer.setDragImage(img, 0, 0);
        }}
      >
        <div
          draggable
          onDrag={onDrag}
          onDragStart={(event) => {
            onDragSizeStart();

            let img = new Image();
            img.src =
              "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
            event.dataTransfer.setDragImage(img, 0, 0);
          }}
          onDragEnd={onDragSizeEnd}
          style={{
            position: "absolute",
            left: -15,
            top: -15,
          }}
          className={styles.createImageQuiz_leftSizeOpt}
        />

        <div
          draggable
          onDrag={onDrag2}
          onDragEnd={onDragSizeEnd}
          onDragStart={(event) => {
            onDragSizeStart();

            let img = new Image();
            img.src =
              "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
            event.dataTransfer.setDragImage(img, 0, 0);
          }}
          style={{
            right: -15,
            bottom: -15,
          }}
          className={styles.createImageQuiz_rightSizeOpt}
        />
      </div>
    </div>
  );
};

export default CreateImageQuiz;
