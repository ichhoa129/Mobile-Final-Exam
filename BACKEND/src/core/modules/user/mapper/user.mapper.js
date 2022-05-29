export function mapToModelByUserCreationDto(userCreationDto) {
    return {
        email: userCreationDto.email,
        password: userCreationDto.password,
        username: userCreationDto.username,
    };
}

export function mapToModelByUserUpdateDto(updateProfileDto) {
    return {
        status: updateProfileDto.status,
    };
}
