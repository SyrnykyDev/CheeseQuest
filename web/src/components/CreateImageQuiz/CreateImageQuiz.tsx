"use client";
import React from "react";
import createImageQuizContainer from "@/components/CreateImageQuiz/CreateImageQuiz.container";

const CreateImageQuiz = () => {
  const {
    states: { position, position2 },
    functions: { setPosition, onDrag, onDrag2 },
  } = createImageQuizContainer();
  return (
    <div
      style={{
        position: "relative",
        // width: "500px",
        // height: "500px",
        // margin: "auto",
      }}
    >
      <div
        style={{
          width: position2.x - position.x,
          height: position2.y - position.y,

          position: "absolute",
          left: position.x,
          bottom: -position2.y,
          backgroundColor: "yellow",
          cursor: "pointer",
        }}
      >
        <div
          draggable
          onDrag={onDrag}
          onDragStart={(event) => {
            let img = new Image();
            img.src =
              "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
            event.dataTransfer.setDragImage(img, 0, 0);
          }}
          style={{
            position: "absolute",
            left: -30,
            top: -30,
            // left: position.x,
            // top: position.y,
            width: "40px",
            height: "40px",
            backgroundColor: "green",
            cursor: "pointer",
          }}
        />

        <div
          draggable
          onDrag={onDrag2}
          onDragStart={(event) => {
            let img = new Image();
            img.src =
              "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
            event.dataTransfer.setDragImage(img, 0, 0);
          }}
          style={{
            position: "absolute",
            right: -30,
            bottom: -30,
            width: "40px",
            height: "40px",
            backgroundColor: "red",
            cursor: "pointer",
          }}
        />
      </div>
    </div>
  );
};

export default CreateImageQuiz;
