import { ImageService } from '../../modules/image/image.service';
import { CreateUserDto, UpdateUserDto } from '../../modules/user/dto';
import { ValidHttpResponse } from '../../../packages/handler/response/validHttp.response';

class Controller {
    constructor() {
        this.service = ImageService;
    }

    findAll = async req => {
        const { page = 1, size = 24 } = req.query;
        const data = await this.service.findAll(page, size);
        const pagination = await this.service.count(page, size);
        return new ValidHttpResponse.toOkResponse({
            data,
            meta: pagination
        });
    };

    updateOne = async req => {
        await this.service.upsertOne(UpdateUserDto(req.body), req.user.payload.userId);
        return ValidHttpResponse.toNoContentResponse();
    };

    createOne = async req => {
        const data = await this.service.createOne(CreateUserDto(req.body));
        return ValidHttpResponse.toCreatedResponse(data[0]);
    };

    findById = async req => {
        const data = await this.service.findById(req.params.id);
        return ValidHttpResponse.toOkResponse(data);
    };
}

export const ImageController = new Controller();
