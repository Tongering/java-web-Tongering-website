let TONGERING_GAME_OBJECTS = [];

class TongeringGameObject{
    constructor() {
        TONGERING_GAME_OBJECTS.push(this);

        this.timedelta = 0;
        this.has_call_start = false;
    }

    start() {
        
    }

    update() {
        
    }

    destroy() {
        for (let i in TONGERING_GAME_OBJECTS) {
            if (TONGERING_GAME_OBJECTS[i] === this) {
                TONGERING_GAME_OBJECTS.splice(i, 1);
                break;
            }
        }
    }
}

let last_timestamp;

let TONGERING_GAME_OBJECTS_FRAME = (timestamp) => {
    for (let obj of TONGERING_GAME_OBJECTS) {
        if (!obj.has_call_start) {
            obj.start();
            obj.has_call_start = true;
        }
        else {
            obj.timedelta = timestamp - last_timestamp;
            obj.update();
        }
    }
    last_timestamp = timestamp;
    requestAnimationFrame(TONGERING_GAME_OBJECTS_FRAME);
}

requestAnimationFrame(TONGERING_GAME_OBJECTS_FRAME);

export {
    TongeringGameObject
}